#-------------------------------------------------------------------------------
# Copyright (C) 2018 Andreas Redmer <ar-appleflinger@abga.be>
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

error_file=/tmp/imgerrors.log
rm -f $error_file
touch $error_file

echo 
echo checking transparent pixels
for pngfile in  metadata/*/images/featureGraphic.png metadata/*/images/*/*.png
do
	echo checking $pngfile ...
	# identify -verbose $pngfile | grep bKGD
	count=`identify -verbose $pngfile | grep bKGD | wc -l`
	[[ $count -eq "1" ]] || echo "ERROR $pngfile contains transparent pixels" | tee -a $error_file

	# also fix it, in case of local copy (pre-comit) commit can be attempted again
	[[ $count -eq "1" ]] || echo fixing ....
	[[ $count -eq "1" ]] || convert $pngfile -background "#552200" -alpha remove $pngfile

done
echo 


error_count=`cat $error_file | wc -l`
echo 
echo found $error_count errors:
cat $error_file
exit $error_count

