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
#!/bin/bash

# Check if cmdline-tools does not exists
if [ ! -d $ANDROID_HOME/cmdline-tools ]; then

  apt-get --quiet update --yes
  apt-get --quiet install --yes wget tar unzip lib32stdc++6 lib32z1

  # Here we are installing androidSDK tools from official source,
  # (the key thing here is the url from where you are downloading these sdk tool for command line, so please do note this url pattern there and here as well)
  # after that unzipping those tools and
  # then running a series of SDK manager commands to install necessary android SDK packages that'll allow the app to build
  wget --output-document=$ANDROID_HOME/cmdline-tools.zip https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_SDK_TOOLS}_latest.zip
  # move to the archive at ANDROID_HOME
  cd $ANDROID_HOME
  unzip -d cmdline-tools cmdline-tools.zip

  export PATH=$PATH:${ANDROID_HOME}/cmdline-tools/cmdline-tools/bin/

  # Nothing fancy here, just checking sdkManager version
  sdkmanager --version

  # use yes to accept all licenses
  yes | sdkmanager --sdk_root=${ANDROID_HOME} --licenses || true
  sdkmanager --sdk_root=${ANDROID_HOME} "platforms;android-${ANDROID_COMPILE_SDK}"
  sdkmanager --sdk_root=${ANDROID_HOME} "platform-tools"
  sdkmanager --sdk_root=${ANDROID_HOME} "build-tools;${ANDROID_BUILD_TOOLS}"

fi
