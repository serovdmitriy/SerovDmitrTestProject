#!/bin/sh

src="temp"
cd "$src"
for f in *; do
  mv "$f" "img_country_${f#Country=}"
done

GREEN=$'\e[0;32m'
NC=$'\e[0m'
echo "${GREEN}Rename files finished ${NC}"