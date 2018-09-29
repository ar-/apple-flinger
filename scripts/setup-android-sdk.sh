#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2017 Andreas Redmer <ar-appleflinger@abga.be>
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

ANDROID_TARGET_SDK="24"
ANDROID_BUILD_TOOLS="24.0.0"
ANDROID_SDK_TOOLS="24.4.1"

echo Setting up Android SDK
apt-get --quiet install --yes wget tar unzip lib32stdc++6 lib32z1
wget --quiet --output-document=android-sdk.tgz https://dl.google.com/android/android-sdk_r${ANDROID_SDK_TOOLS}-linux.tgz
tar --extract --gzip --file=android-sdk.tgz
mkdir -p tmp
rm -rf tmp/android-sdk-linux
mv android-sdk-linux tmp/
export ANDROID_HOME=$PWD/tmp/android-sdk-linux
echo y | tmp/android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter android-${ANDROID_TARGET_SDK},platform-tools,build-tools-${ANDROID_BUILD_TOOLS},extra-android-m2repository
echo Finished setting up Android SDK

