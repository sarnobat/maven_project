#mvn --quiet --settings ~/sarnobat.git/mac/.m2/settings.xml clean install -Dsurefire.useFile=false -Dsurefire.printSummary=false -Dmaven.test.skip=true

#mvn --quiet --settings ~/sarnobat.git/mac/.m2/settings.xml exec:java -Dexec.mainClass=Coagulate -Dexec.args="--port 4451"
mvn --quiet --settings ~/sarnobat.git/mac/.m2/settings.xml install exec:java -Dexec.mainClass=Md5RoCopyFile  -Dexec.args="4486 /home/sarnobat/sarnobat.git/db/md5ro/md5_files.txt"
