set PATH=%PATH%;C:\sviluppo\postgresql-9.5.5-1\pgsql\bin
REM password=admin
REM initdb -D ./data -U postgres -W
initdb -D ./data -U postgres --pwfile=./.pgpass