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
