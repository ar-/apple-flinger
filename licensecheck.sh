EXCLUDE="./tmp/*"
# check source
RET=`licensecheck \`find . -name "*.java" -not -path "$EXCLUDE"\` | grep -v "GPL (v3 or later)" | grep -v "GENERATED FILE" | wc -l`
if [ $RET -eq 0 ]; then
	echo all copyrights okay
else
	licensecheck `find . -name "*.java" -not -path "$EXCLUDE"` | grep -v "GPL (v3 or later)" | grep -v "GENERATED FILE"
	echo ERROR : there are $RET files with wrong copyright - listed above
	exit 1
fi

#check COPYING files
LICSHA=`find . -name "COPYING" -not -path "$EXCLUDE" | xargs sha512sum | egrep -o "[a-f0-9]+ " | sort -u`
LICMD5=`find . -name "COPYING" -not -path "$EXCLUDE" | xargs    md5sum | egrep -o "[a-f0-9]+ " | sort -u`
if [ $LICSHA = "7633623b66b5e686bb94dd96a7cdb5a7e5ee00e87004fab416a5610d59c62badaf512a2e26e34e2455b7ed6b76690d2cd47464836d7d85d78b51d50f7e933d5c" ]; then
	if [ $LICMD5 = "d32239bcb673463ab874e80d47fae504" ]; then
		echo all license files okay
	else
		echo ERROR : MD5 of COPYING file has changed
		exit 1
	fi
else
	echo ERROR : SHA of COPYING file has changed
	exit 1
fi


