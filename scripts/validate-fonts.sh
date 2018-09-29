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
tmpfile=/tmp/font.xml
for font in `ls android/assets/*.?tf`
do
	echo checking $font
	ttx -o $tmpfile -tcmap $font

	# iterate each char (each 3 bytes in utf8)
	for ch in `cat android/assets/af_ru.properties | egrep -o "."`
	do
		echo ----
		echo $ch
		echo $ch | hexdump
	done
done

