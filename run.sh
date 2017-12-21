#mvn --quiet --settings ~/sarnobat.git/mac/.m2/settings.xml clean install -Dsurefire.useFile=false -Dsurefire.printSummary=false -Dmaven.test.skip=true

mvn --quiet --settings ~/sarnobat.git/mac/.m2/settings.xml exec:java -Dexec.mainClass=Coagulate -Dexec.args="--port 4451"