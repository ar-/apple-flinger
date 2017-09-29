./gradlew android assembleRelease
jarsigner -verbose -sigalg SHA1withRSA -digestalg SHA1 -keystore android_keystore.jks android/build/outputs/apk/android-release-unsigned.apk appleslinger 
mv android/build/outputs/apk/android-release-unsigned.apk android/build/outputs/apk/android-release-signed.apk
zipalign -f 4 android/build/outputs/apk/android-release-signed.apk android/build/outputs/apk/android-release-final.apk
rm -f android/build/outputs/apk/android-release-signed.apk


