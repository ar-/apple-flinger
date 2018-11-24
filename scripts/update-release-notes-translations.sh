#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2017-2018 Andreas Redmer <ar-appleflinger@abga.be>
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#-------------------------------------------------------------------------------

md=metadata
orig=en-AU
origd="$md/$orig"
cl=changelogs
todo="af am ar bg bo ca cs de el eo es et eu fa fr hi hr hu id it ja ko lt lv nb nl no pl pt-BR ro ru sl sr sv sw th tr uk vi zh-CN zh-TW"
reps=0

function translate {
  language=$1
  fromfile=$2
  tofile=$3

  echo "translating en:$language file $fromfile into $tofile"

  torify trans -b -i $fromfile en:$language > /tmp/trans.txt

  # get a new public IP forr the next run
  echo -e 'AUTHENTICATE ""\r\nsignal NEWNYM\r\nQUIT' | nc 127.0.0.1 9051
  
  #repair auto translate formating
  sed -i 's/^\* /  \* /g' /tmp/trans.txt
  sed -i 's/^\*/  \* /g' /tmp/trans.txt
  sed -i 's/ null$//g' /tmp/trans.txt

  #replace tofile only if it has changed or has not existed yet
  if [ ! -f $tofile ]
  then
    ((reps++))
    echo "file $tofile not there yet. creating it $reps "
    touch $tofile
  fi

  resultsize=`stat --printf="%s" /tmp/trans.txt`
  if (( resultsize < 20 )); then
    echo $I is too small. not using it
    echo waiting 10 minutes
    sleep 1
  else
    echo "file size is fine, check if we need to replace it"
    diff /tmp/trans.txt $tofile
    isdiff=$?
    if [ $isdiff -ne "0" ]
    then
      ((reps++))
      echo "file $tofile has changed. replacing it $reps "
      cat /tmp/trans.txt > $tofile
    fi
  fi

}

# program starts here
cldir_from="$origd/$cl"
latestfile=`ls $cldir_from | sort -n | tail -n1`

# init all target file with 0 bytes (assume that all dirs exist, otherwise run twice)
for I in $todo
do
  touch metadata/$I/changelogs/$latestfile
  resultsize=`stat --printf="%s" metadata/$I/changelogs/$latestfile`
  if (( resultsize < 20 )); then
      echo $I is still small
  #else
      #echo "fine"
  fi
done

# order todolist by size
todo=`ls -laSr metadata/*/changelogs/$latestfile | egrep -o "metadata/.*" | sed "s/metadata\///g" |  sed "s/\/changelogs.*//g"`
#ls -laSr metadata/*/changelogs/$latestfile
#exit

if [ ! -d $md ]
then
  echo "meta dir not found"
  exit 2
fi

if [ ! -d $origd ]
then
  echo "orig dir not found"
  exit 3
fi

for I in $todo
do
  echo "next language is $I"
  ld="$md/$I"
  if [ ! -d $ld ]
  then
    echo "lingo dir $ld not found"
    #mkdir $ld
    exit 4
  fi

  cldir_from="$origd/$cl"
  cldir_to="$ld/$cl"
  mkdir -p $cldir_to

  # iterate all content files
  # pick only the latest file
  for F in `ls $cldir_from | sort -n | tail -n1`
  do
    translate $I $cldir_from/$F $cldir_to/$F 

    echo waiting 30 secs to give google a break
    sleep 3
  done
done

# updating the status for each commit is way to complex and disturbing to other procedures
# updateing it for each release is fine

# need to build the latest release apkm so the size is correct in status
# also ensuring that it actually builds it good
ANDROID_HOME=~/devel/android-sdk-linux/ ./gradlew assembleRelease || exit 1

# update projet status
scripts/update-project-status.sh


if [ $reps == "0" ]
then
  echo "no replacements done. all good"
else
  echo "had $reps translations replaced. please rerun"
  exit 1
fi




