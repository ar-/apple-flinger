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

