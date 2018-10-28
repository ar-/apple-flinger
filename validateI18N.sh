#-------------------------------------------------------------------------------
# Copyright (C) 2017-2018 Andreas Redmer <ar-appleflinger@abga.be>
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
error_file=/tmp/i18nerrors.log
achi_file=/tmp/tmp_achi_file.txt
rm -f $error_file
touch $error_file
in_source=`egrep -a -r "I18N.getString|I18N.s" core/ | egrep -a -o "I18N.getString.*)|I18N.s.*)" | sed "s/I18N/\nI18N/g" | egrep -a -o "I18N.getString.*\")|I18N.s.*\")" | egrep -a -o '".*"' | sort -u | egrep -o "[a-zA-Z0-9_]*" | sort -u`
achi=`cat core/src/com/gitlab/ardash/appleflinger/helpers/Achievement.java | egrep "\sACH_" | tr '[:upper:]' '[:lower:]' | egrep -o "[a-z_]*"`
prop_files=`ls android/assets/af*properties`

all_achis=''
for a in $achi
do
	all_achis=`echo $all_achis name_$a desc_$a`
done
echo $all_achis > $achi_file
in_source=`echo $in_source $all_achis`

# check string in java code that do not come up in prop files
echo these are i18n strings in the source code
echo $in_source
echo 
echo checking all of them ...
echo 
#exit
for ins in $in_source
do
#	echo checking: $ins

	for pro in $prop_files
	do
#		echo in prop file: $pro
		NUM=`egrep -a "^$ins=" $pro | wc -l`
		[[ $NUM -eq "1" ]] || echo ERROR $ins is in Java code but not in $pro | tee -a $error_file
	done
done

# check strings in prop files, that do not come up in java code
# they must be removed to avoid useless translation load for future property files
echo 
echo checking values in prop files that are not used in java source code
for pro in $prop_files
do
#	echo in prop file: $pro
	in_this_prop=`egrep -a -o "^.*=" $pro | tr -d '='`

	for fin in $in_this_prop
	do
#		echo searching for uses of: $fin
		NUM=`egrep -a -r "I18N.getString\(\"$fin\"\)|I18N.s\(\"$fin\"\)" core/ | wc -l`
		NUM2=`echo $all_achis | egrep -a $fin | wc -l`
#		echo occurences of $fin in java code: $NUM 
#		echo occurences of $fin in java enums: $NUM2 
		[[ $NUM -ge "1" ]] || [[ $NUM2 -ge "1" ]] || echo ERROR $fin in $pro does not occur in any java code | tee -a $error_file
	done

#	[[ $NUM -eq $number_of_ins ]] || echo ERROR $pro contains $NUM lines, but in java code there are $number_of_ins references | tee -a $error_file
done


# double check all the numbers
echo 
echo checking number of lines in prop files
number_of_ins=`echo $in_source | wc -w`
for pro in $prop_files
do
#	echo in prop file: $pro
	NUM=`egrep -a "^.*=" $pro | wc -l`
	[[ $NUM -eq $number_of_ins ]] || echo ERROR $pro contains $NUM lines, but in java code there are $number_of_ins references | tee -a $error_file
done

# check file encoding. must be UTF-8
echo 
echo checking file encodings to be UTF8
file -L android/assets/af*.properties 
wrong_file_enc_count=`file -L android/assets/af*.properties | egrep -v "UTF-8 Unicode text|ASCII text" | wc -l`
[[ $wrong_file_enc_count -eq "0" ]] || echo "ERROR at least one file ($wrong_file_enc_count) is not UTF-8, please run: iconv -f ISO-8859-15 -t UTF-8 android/assets/af_de.properties > tmp.properties" | tee -a $error_file
echo 
file -L android/assets/af*.properties | egrep -v "UTF-8 Unicode text|ASCII text"
echo 

# check escaped hashtags in twitter recommendation texts
echo 
echo checking escaped hashtags in twitter recommendation texts
grep -i " #" android/assets/af_*.properties
wrong_file_enc_count=`grep -i " #" android/assets/af_*.properties | wc -l`
[[ $wrong_file_enc_count -eq "0" ]] || echo "ERROR at least one file ($wrong_file_enc_count) has the twitter hashtag not escaped, error was corrected. please run again" | tee -a $error_file

# correct it with sed (only if it was wrong, otherwise it confuses the git staging process)
[[ $wrong_file_enc_count -eq "0" ]] || sed -i -e 's/ #/ \\#/g' `find  android/assets/af*.properties -maxdepth 1 -type f`
echo 

# check for … character
echo 
echo check for … character
grep -i "…" android/assets/af_*.properties
wrong_file_enc_count=`grep -i "…" android/assets/af_*.properties | wc -l`
[[ $wrong_file_enc_count -eq "0" ]] || echo "ERROR at least one file ($wrong_file_enc_count) has a … UTF character. remove it" | tee -a $error_file
echo 

# check for dots
echo 
echo checking for dots
grep -i "\." android/assets/af*.properties | egrep -v "# " | grep -v twitter
wrong_file_enc_count=`grep -i "\." android/assets/af*.properties | egrep -v "# " | grep -v twitter | wc -l`
[[ $wrong_file_enc_count -eq "0" ]] || echo "ERROR at least one file ($wrong_file_enc_count) has a dot. remove it" | tee -a $error_file
echo 

# check for dots
echo 
echo checking for backslash-n or backslash-t
egrep -i '\\n|\\t|\\r'  android/assets/af*.properties
wrong_file_enc_count=`grep -F "\n"  android/assets/af*.properties | wc -l`
[[ $wrong_file_enc_count -eq "0" ]] || echo "ERROR at least one file ($wrong_file_enc_count) has a backslash-n or backslash-t. remove it" | tee -a $error_file
echo 

error_count=`cat $error_file | wc -l`
echo 
echo found $error_count errors:
cat $error_file
exit $error_count

