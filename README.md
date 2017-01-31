<h1>Multiple databases</h1>

<h2>GIT USAGE</h2>
<p>
git config --global http.proxy http://icttb0:xxx@tmgproxy.esselunga.net:8080    <BR>
git init                                                                        <BR>
git add README.md                                                               <BR>
git commit -m "first commit"                                                    <BR>
git remote add origin https://github.com/tommasoborgato/poc-db.git              <BR>
git push -u origin master                                                       
</p>

<h1>
##########################################                                      <BR>
###### Postgres ##########################                                      <BR>
##########################################                                      
</h1>
<p>
C:\sviluppo\postgresql-9.5.5-1\pgsql                                                                                      <BR>
Size 173 MB                                                                                                               <BR>
PostgreSQL is released under the PostgreSQL License, a liberal Open Source license, similar to the BSD or MIT licenses.   <BR>
per inizializzare il database:
    ./Postgres/poc-initdb.bat
    => crea il folder ./Postgres/data
per start del DB:
    ./Postgres/poc-startdb.bat
    => crea il log ./Postgres/logfile.log
per stop del DB:
    ./Postgres/poc-stopdb.bat
per creare l'utente POC:
    ./Postgres/poc-create-schema.sql
per creare una tabella in POC:
    ./Postgres/poc-create-schema-tables.sql    
per connettersi al DB installare pgadmin (localhost:5432 postgres/admin)
</p>

<h1>
##########################################
###### MySql #############################
##########################################
</h1>
<p>
C:\sviluppo\mysql-5.7.17-winx64                                                                         <BR>
Size 1.8 GB                                                                                             <BR>
"MySQL Community Edition" GPL license : you can sell it under GPL as long as you give all source code   <BR>
</p>


