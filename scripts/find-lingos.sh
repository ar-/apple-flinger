#!/bin/bash
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
