#!/bin/bash
ALL=`ls android/assets/af_* | egrep -o "af_.*\.pro" | egrep -o ".*\." | tr -d '.' | sed -e "s/^af_//" | tr '[:upper:]' '[:lower:]' | tr '-' '_' | sort`
INCODE=`cat core/src/com/gitlab/ardash/appleflinger/screens/LanguageDialog.java | grep "t\.FLAG" | egrep -o "FLAG_.*," | tr -d ',' | tr '[:upper:]' '[:lower:]' | sed -e "s/^flag_//"  | sort`
echo We have files for all these:
echo $ALL

echo In code we have all these:
echo $INCODE

NOTINCODE=$ALL
for I in $INCODE
do
	# echo $I
	NOTINCODE=${NOTINCODE//pt_br/}
	NOTINCODE=${NOTINCODE//pt_pt/}
	NOTINCODE=${NOTINCODE//nb_no/}
	NOTINCODE=${NOTINCODE//$I/}
done

echo these one are not in code:
echo $NOTINCODE

# items in a script we can not cover due to fonttype limitations
NOTINCODE=${NOTINCODE//ko/}
NOTINCODE=${NOTINCODE//sr_cyrl/}

echo some of them are covered, that leaves us with the remainder we can still add:
echo $NOTINCODE
