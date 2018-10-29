#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2018 Andreas Redmer <andreasredmer@mailchuck.com>
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

export ANDROID_HOME=~/devel/android-sdk-linux/
./gradlew android assembleRelease
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore android_keystore.jks android/build/outputs/apk/android-release-unsigned.apk appleslinger 
mv android/build/outputs/apk/android-release-unsigned.apk android/build/outputs/apk/android-release-signed.apk
zipalign -f 4 android/build/outputs/apk/android-release-signed.apk android/build/outputs/apk/android-release-final.apk
rm -f android/build/outputs/apk/android-release-signed.apk
echo product is here:
ls -la android/build/outputs/apk/android-release-final.apk