cd ~/ddsnake~ddsnake-source-code-repository/GymRankingsFileWriter

scp -i cs184-dfrankl-stanford-edu.pem output/index.html ubuntu@54.235.71.45:/home/../var/www/
scp -i cs184-dfrankl-stanford-edu.pem output/menIndiv.html ubuntu@54.235.71.45:/home/../var/www/
scp -i cs184-dfrankl-stanford-edu.pem output/womenIndiv.html ubuntu@54.235.71.45:/home/../var/www/
scp -i cs184-dfrankl-stanford-edu.pem output/womenTeam.html ubuntu@54.235.71.45:/home/../var/www/