#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2018 Andreas Redmer <andreasredmer@mailchuck.com>
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

md=metadata
orig=en-AU
origd="$md/$orig"
cl=changelogs
todo=`ls metadata/*/images/phoneScreenshots/M_1_5.png | egrep -o "metadata/.*/images" | egrep -o "/.*/"`
reps=0

for I in $todo
do
  echo "next language is $I"
  ls metadata/$I/images/phoneScreenshots/M_1_5.png
  #example: convert metadata/en-AU/images/phoneScreenshots/M_1_5.png -crop 1024x500+128+220\!  metadata/en-AU/images/featureGraphic.png
  # crop (vertical offset works for both font types)
  convert metadata/$I/images/phoneScreenshots/M_1_5.png -crop 1024x500+128+195\!  metadata/$I/images/featureGraphic.png
  # convert transparant slingerband to dark brown
  convert metadata/$I/images/featureGraphic.png -background "#552200" -alpha remove metadata/$I/images/featureGraphic.png

done

