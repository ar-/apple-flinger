RET=`licensecheck -i "android-sdk-linux" \`find . -name "*.java"\` | grep -v "GPL (v3 or later)" | grep -v "GENERATED FILE" | wc -l`
if [ $RET -eq 0 ]; then
	echo all copyrights okay
	exit $RET
else
	licensecheck -i "android-sdk-linux" `find . -name "*.java"` | grep -v "GPL (v3 or later)" | grep -v "GENERATED FILE"
	echo ERROR : there are $RET files with wrong copyright - listed above
	exit 1
fi
