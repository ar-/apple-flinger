#!/bin/bash

if [ ! -f "AUTHORS.md" ]
then
  echo "AUTHORS.md not found. you mudt be in the wrong directory"
  exit 2
fi

echo "collecting authors"
echo -e "Authors\n=======\nWe'd like to thank the following people for their contributions.\n\n" > /tmp/AUTHORS.md
git log --raw | grep "^Author: " | sort | uniq | cut -d ' ' -f2- | sed 's/^/- /' >> /tmp/AUTHORS.md
echo "diffing"
diff /tmp/AUTHORS.md AUTHORS.md
di=$?
if [ $di == "0" ]
then
  echo "no new authors found"
else
  echo "new authors found. updating file. commit again"
  cat /tmp/AUTHORS.md > AUTHORS.md
  exit 1
fi


