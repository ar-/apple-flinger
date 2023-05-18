#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2023 Andreas Redmer <ar-appleflinger@abga.be>
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

# call this script like this 
# make-release.sh 1005013

NR=$1
#NN=$2
NN="${NR:0:1}.${NR:3:1}.${NR:5:2}"
CR=`cat android/AndroidManifest.xml | grep versionCode | egrep -o "[0-9]+"`
CN=`cat android/AndroidManifest.xml | grep versionName | egrep -o "[0-9\.]+"`
echo "current release: $CR"
echo "next release   : $NR"
echo "current name   : $CN"
echo "next name      : $NN"

echo correct? if not ctrl+C now
read


# Define the file path
file_path="android/AndroidManifest.xml"

# Replace the versionCode value
sed -i "s/android:versionCode=\"$CR\"/android:versionCode=\"$NR\"/" $file_path

# Replace the versionName value
sed -i "s/android:versionName=\"$CN\"/android:versionName=\"$NN\"/" $file_path

echo write changelog here >> metadata/en/changelogs/$NR.txt
nano metadata/en/changelogs/$NR.txt

/bin/bash ./scripts/update-release-notes-translations.sh 
/bin/bash ./scripts/update-release-notes-translations.sh 

git add metadata/
git commit . -m "updated release $CN to $NN and added release notes"

echo DONE
echo ++++++++++++++++++++++++++++++
RN=`cat metadata/en/changelogs/$NR.txt`
echo use this tag:
echo v$NN
echo use this release note for the tag after push:
echo $RN
