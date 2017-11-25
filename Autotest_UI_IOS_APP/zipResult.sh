#!/bin/sh

cp -R ./initdata   ./target/site/
cp -R ./screenshot ./target/site/  
cp -R ./results    ./target/site/

zip -r htmlResultOfMvn.zip  ./target/site/ 
