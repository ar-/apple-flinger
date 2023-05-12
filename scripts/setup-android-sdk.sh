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

  # ANDROID_COMPILE_SDK is the version of Android you're compiling with.
  # It should match compileSdkVersion.
export ANDROID_COMPILE_SDK="30"
  # ANDROID_BUILD_TOOLS is the version of the Android build tools you are using.
  # It should match buildToolsVersion.
export ANDROID_BUILD_TOOLS="30.0.0"
  # It's what version of the command line tools we're going to download from the official site.
  # Official Site-> https://developer.android.com/studio/index.html
  # There, look down below at the cli tools only, sdk tools package is of format:
  #        commandlinetools-os_type-ANDROID_SDK_TOOLS_latest.zip
  # when the script was last modified for latest compileSdkVersion, it was which is written down below
export ANDROID_SDK_TOOLS="9477386"

apt-get --quiet update --yes
apt-get --quiet install --yes wget tar unzip lib32stdc++6 lib32z1
wget --quiet --output-document=android-sdk.zip https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_SDK_TOOLS}_latest.zip
unzip -d android-sdk-linux android-sdk.zip
echo y | android-sdk-linux/cmdline-tools/bin/sdkmanager "platforms;android-${ANDROID_COMPILE_SDK}" >/dev/null
echo y | android-sdk-linux/cmdline-tools/bin/sdkmanager "platform-tools" >/dev/null
echo y | android-sdk-linux/cmdline-tools/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS}" >/dev/null
export ANDROID_HOME=$PWD/android-sdk-linux
export PATH=$PATH:$PWD/android-sdk-linux/platform-tools/
chmod +x ./gradlew
# temporarily disable checking for EPIPE error and use yes to accept all licenses
set +o pipefail
yes | android-sdk-linux/tools/bin/sdkmanager --licenses
set -o pipefail

exit 0

echo Setting up Android SDK
apt-get --quiet install --yes wget tar unzip lib32stdc++6 lib32z1 sdkmanager
wget --quiet --output-document=android-sdk.tgz https://dl.google.com/android/android-sdk_r${ANDROID_SDK_TOOLS}-linux.tgz
tar --extract --gzip --file=android-sdk.tgz
mkdir -p tmp
rm -rf tmp/android-sdk-linux
mv android-sdk-linux tmp/
export ANDROID_HOME=$PWD/tmp/android-sdk-linux

# a machine cannot agree to a licence, but for the idiots @ google we do it anyway
mkdir tmp/android-sdk-linux/licenses
printf "8933bad161af4178b1185d1a37fbf41ea5269c55\nd56f5187479451eabf01fb78af6dfcb131a6481e" > tmp/android-sdk-linux/licenses/android-sdk-license
printf "84831b9409646a918e30573bab4c9c91346d8abd" > tmp/android-sdk-linux/licenses/android-sdk-preview-license

echo y | tmp/android-sdk-linux/tools/android --silent update sdk --no-ui --all --filter android-${ANDROID_TARGET_SDK},platform-tools,build-tools-${ANDROID_BUILD_TOOLS},extra-android-m2repository


echo Setup SDK manager 28
yes | ./sdkmanager "platforms;android-28"
yes | ./sdkmanager "build-tools;28.0.3"
yes | ./sdkmanager --licenses
yes | tmp/android-sdk-linux/tools/android tmp/android-sdk-linux/tools/lib/sdkmanager.jar --licenses

echo Finished setting up Android SDK

