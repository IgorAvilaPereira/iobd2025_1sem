#!/bin/bash  
./atualizar.sh
repositorio=${PWD##*/}
mkdir $1 && cd $1 && touch "README.md" && echo "https://download-directory.github.io/?url=http://github.com/IgorAvilaPereira/$repositorio/tree/main/$1" >> "README.md" && echo "&nbsp;" >> "README.md" && cd ./../ && git add * && git commit -m $1 && git push 
#mkdir $1 && cd $1 && touch "$1.md" && echo "$1 <br>" >> "$1.md" && cd ./../ && git add * && git commit -m $1 && git push 
# se n tiver wiki, sera preciso comentar algumas linhas do script abaixo
./atualizar.sh 
