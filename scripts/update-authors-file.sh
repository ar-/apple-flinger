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
  echo "AUTHORS.md not found. you mudt be in the wrong directory"
  exit 2
fi

echo "collecting authors"
echo -e "Authors\n=======\nWe'd like to thank the following people for their contributions:\n\n" > /tmp/AUTHORS.md
git log --raw | grep "^Author: " | sort | uniq | cut -d ' ' -f2- | sed 's/^/- /' >> /tmp/AUTHORS.md

# remove authors that don't want to be inlcuded or old email addresses 
for AE in ar-appleflinger@abga.be ar-gitlab@abga.be
do
  echo excluding $AE
  cat /tmp/AUTHORS.md | grep -v "$AE" > /tmp/AUTHORS_tmp.md
  cp /tmp/AUTHORS_tmp.md /tmp/AUTHORS.md
done

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
  exit 1
fi


