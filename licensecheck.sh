sudo apt-get install -y licensecheck
RET=`licensecheck \`find . -name "*.java"\` | grep -v "GPL (v3 or later)" | grep -v "GENERATED FILE" | wc -l`
if [ $RET -eq 0 ]; then
	echo all copyrights okay
	exit $RET
else
	echo there are $RET files with wrong copyright
	licensecheck `find . -name "*.java"` | grep -v "GPL (v3 or later)" | grep -v "GENERATED FILE"
	exit 1
fi
