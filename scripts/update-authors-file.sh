#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2017,2018 Andreas Redmer <ar-appleflinger@abga.be>
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

echo "collecting authors"
echo -e "Authors\n=======\nWe'd like to thank the following people for their contributions:\n\n" > /tmp/AUTHORS.md
git log --raw | grep "^Author: " | sort | uniq | cut -d ' ' -f2- | sed 's/^/- /' >> /tmp/AUTHORS.md

# remove authors that don't want to be inlcuded or old email addresses 
excl="ar-gitlab@abga.be ar-gdroid@abga.be andreasredmer@mailchuck.com a_r@posteo.de vagrant@vagrant.vm michal@cihar.com hosted@weblate.org"
for AE in $excl
do
  echo excluding $AE
  cat /tmp/AUTHORS.md | grep -v "$AE" > /tmp/AUTHORS_tmp.md
  cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md
done

# exclude one of Iván who commited with 2 email addresses (can't be covered by the simple grep above)
cat /tmp/AUTHORS.md | grep -v "Iván <ivanrsm1997@gmail.com>" > /tmp/AUTHORS_tmp.md
cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md

# exclude Markels duplicate
cat /tmp/AUTHORS.md | grep -v "Markel @wakutiteo" > /tmp/AUTHORS_tmp.md
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

  # also update in-code authors that helped with the translation
  excl_regex=`echo $excl | tr ' ' '|'`

  # collect authors of property files, exclude the excluded, remove email addresses and put all in one line
  # unfortunatally also replace cyrillic names with the latin transcription, because the libgdx label, looks too messed up otherwise
  translators=`git log --raw android/assets/af*.properties | grep "^Author: " | sort | uniq | cut -d ' ' -f2- | egrep -v $excl_regex | egrep -o ".* <" | egrep -o ".* " | sed -e "s/Сухичев Михаил Иванович/Sukhichev Mikhail Ivanovich/g" | sed -e "s/xxssmaoxx/Simon Dottor/g" | sed -e "s/Markel @wakutiteo/Markel/g" | sort -u | tr '\n' ',' | sed -e "s/ ,/, /g"`
  
  # repair Iván who come up twice
  translators=${translators/Iván, Iván Seoane/Iván Seoane}

  # remove Markels duplicate


  # smack it into the source code
  sed -i "s/.*static String translators.*/\tstatic String translators=\"${translators}\";/" core/src/com/gitlab/ardash/appleflinger/screens/CreditsDialog.java
  echo "possibly added translators to the credit screen. should be checked in both font types?"
  exit 1
fi


