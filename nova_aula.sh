#!/bin/bash  
./atualizar.sh
mkdir $2 && cd $2 && touch "README.md" && echo "&nbsp;" >> "https://download-directory.github.io/?url=http://github.com/IgorAvilaPereira/$1/tree/main/$2" && echo "&nbsp;" >> "README.md" && cd ./../ && git add * && git commit -m $2 && git push 
#mkdir $2 && cd $2 && touch "$2.md" && echo "$2 <br>" >> "$2.md" && cd ./../ && git add * && git commit -m $2 && git push 
# se n tiver wiki, sera preciso comentar algumas linhas do script abaixo
./atualizar.sh 
