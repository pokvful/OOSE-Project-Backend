#!/bin/sh


if test -f /var/database.init/setup.txt; then
    echo "Database is already set up!";
    exit 1;
fi

# loop over the result of all sql files
for sql_file in `ls /var/database.init/*.sql`; 
    do mysql --user=root --password=$MYSQL_ROOT_PASSWORD lbc < $sql_file ;
done
touch /var/database.init/setup.txt;
echo "Database is set up! Check for possible errors above!";