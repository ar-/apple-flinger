#!/bin/bash
#-------------------------------------------------------------------------------
# Copyright (C) 2018 Andreas Redmer <ar-gitlab@abga.be.com>
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

contributors=`cat AUTHORS.md | egrep "^-" |wc -l`
translations=`ls android/assets/af_*.properties | wc -l`
commits=`git rev-list --all --count`
reposize=`git count-objects -vH | grep "size:" | sed "s/size: //g"`
apksize=`du -hs android/build/outputs/apk/android-release-unsigned.apk | sed "s/\t/ /g" | egrep -o ".* "`
mergedrequests=`wget -O- "https://gitlab.com/ar-/apple-flinger/merge_requests?scope=all&utf8=%E2%9C%93&state=merged" 2>/dev/null | grep Merged | egrep -o "badge badge-pill.*" | egrep -o "[0-9]*" | head -n1`
echo "{contributors:'$contributors',translations:'$translations',commits:'$commits',reposize:'$reposize',apksize:'$apksize',mergedrequests:'$mergedrequests'}" > status.json