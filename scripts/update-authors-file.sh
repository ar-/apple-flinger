#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2017-2023 Andreas Redmer <ar-appleflinger@abga.be>
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

if [ ! -f "AUTHORS.md" ]
then
  echo "AUTHORS.md not found. you must be in the wrong directory"
  exit 2
fi

# find all people who are to blame for hte currently visible translations
INCODE=`cat core/src/com/gitlab/ardash/appleflinger/screens/LanguageDialog.java | grep "t\.FLAG" | egrep -o "FLAG_.*," | tr -d ',' | tr '[:upper:]' '[:lower:]' | sed -e "s/^flag_//"  | sort`
CINCODE=`cat core/src/com/gitlab/ardash/appleflinger/screens/LanguageDialog.java | grep "t\.FLAG" | egrep -o "FLAG_.*," | tr -d ',' | tr '[:upper:]' '[:lower:]' | sed -e "s/^flag_//"  | wc -l`
echo In code we have these $CINCODE translations:
echo $INCODE

CSIN=`echo $INCODE | tr ' ' ','`
echo $CSIN

TFILES=`eval "ls android/assets/af_{$CSIN}.properties"`
CTFILES=`eval "ls android/assets/af_{$CSIN}.properties | wc -l"`

echo For them we have these $CTFILES files:
echo $TFILES

blfile=/tmp/afblames.txt
rm -f $blfile
for tf in $TFILES
do
  git blame $tf >> $blfile
done
echo For the current versions of this file we have these `cat $blfile | grep "=" | egrep -o " \(.*[0-9]-" | egrep -o ".* 2" | sed -e 's/^..//' -e 's/ *.$//' | grep -v '#' | sort -u | wc -l` people commited to them:
cat $blfile | grep "=" | egrep -o " \(.*[0-9]-" | egrep -o ".* 2" | sed -e 's/^..//' -e 's/ *.$//' | grep -v '#' | sort | uniq -c | sort -nr

echo Removing those with less than 10 contrubutions:
echo
#git blame $TFILES | egrep -o " \(.*[0-9]-" | egrep -o ".* 2" | sed -e 's/^..//' -e 's/ *.$//' | grep -v '#' | sort | uniq -c | sort -nr
translators=`cat $blfile | grep "=" | egrep -o " \(.*[0-9]-" | egrep -o ".* 2" | sed -e 's/^..//' -e 's/ *.$//' | grep -v '#' | sort | uniq -c | awk '$1>=10{print substr($0,9)}' | sed -e "s/Сухичев Михаил Иванович/Sukhichev Mikhail Ivanovich/g" | sed -e "s/xxssmaoxx/Simon Dottor/g"  | sed -e "s/Masowick/Demian Masowick/g" | sed -e "s/gallegonovato/Fran Carro/g" | sed -e "s/Fontan030/Roman F\./g" | sort | tr '\n' ','|sed -e "s/,/, /g"`
echo
echo $translators
#rm -f $blfile

echo
echo applying substitutions, leaves us with:

translators=${translators/Andreas, Andreas Redmer/Andreas Redmer}
translators=${translators/Artem/Artem Kovalev}
translators=${translators/Iván/Iván Seoane}
translators=${translators/John Doe, /}
translators=${translators/J. Lavoie/Jeannette Lavoie}
translators=${translators/Michal Čihař, /} # doesnt want to be named
translators=${translators/Oguz Ersen/Oghuz Ersen} # Oğuz is not spported by the font

echo $translators

# exit 0

echo "collecting authors"
echo -e "Authors\n=======\nWe'd like to thank the following people for their contributions:\n\n" > /tmp/AUTHORS.md
git log --raw | grep "^Author: " | sort | uniq | cut -d ' ' -f2- | sed 's/^/- /' >> /tmp/AUTHORS.md

# remove authors that don't want to be inlcuded or old email addresses 
excl="z@z ar-gdroid@abga.be ar-gitlab@abga.be andreasredmer@mailchuck.com a_r@posteo.de vagrant@vagrant.vm michal@cihar.com hosted@weblate.org KovalevArtem.ru@gmail.com istausss.outlook.com jonatan.nyberg.karl@gmail.com Demian@gmx.co.uk Oguz ☆Verdulo admin@sto.ugu.pl"
for AE in $excl
do
  echo excluding $AE
  cat /tmp/AUTHORS.md | grep -v "$AE" > /tmp/AUTHORS_tmp.md
  cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md
done

#exclude another duplicate
AE="Andreas <ar-appleflinger@abga.be>"
echo excluding $AE
cat /tmp/AUTHORS.md | grep -v "$AE" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# exclude one of Iván who commited with 3 email addresses (can't be covered by the simple grep above)
cat /tmp/AUTHORS.md | grep -v "Iván <ivanrsm1997@gmail.com>" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md
cat /tmp/AUTHORS.md | grep -v "Iváns <ivanrsm1997@gmail.com>" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# exclude one of Verdulo who commited with 3 email addresses (can't be covered by the simple grep above)
cat /tmp/AUTHORS.md | grep -v "Verdulo <cybertomek@openmailbox.org>" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md
cat /tmp/AUTHORS.md | grep -v "Verdulo <cybertomek@openmailbox.org>" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# exclude Markels duplicate
cat /tmp/AUTHORS.md | grep -v "Markel @wakutiteo" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# exclude Fontan030 duplicate
cat /tmp/AUTHORS.md | grep -v "Fontan030" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# remove the weblate placeholder users
cat /tmp/AUTHORS.md | grep -v "weblate.org" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# now make the markdown look nice and put some spamprotection on the email addresses
sed -i 's/\./∙/g' /tmp/AUTHORS.md
sed -i 's/\@/⒜/g' /tmp/AUTHORS.md
sed -i 's/</\\</g' /tmp/AUTHORS.md
sed -i 's/>/\\>/g' /tmp/AUTHORS.md


echo "diffing"
diff /tmp/AUTHORS.md AUTHORS.md
di=$?

if [ $di == "0" ]
then
  echo "no new authors found"
else
  echo "new authors found. updating file. commit again"
  cat /tmp/AUTHORS.md > AUTHORS.md

  # smack translators into the source code
  sed -i "s/.*static String translators.*/\tstatic String translators=\"${translators}\";/" core/src/com/gitlab/ardash/appleflinger/screens/CreditsDialog.java
  echo "possibly added translators to the credit screen. should be checked in both font types?"
  exit 1
fi


