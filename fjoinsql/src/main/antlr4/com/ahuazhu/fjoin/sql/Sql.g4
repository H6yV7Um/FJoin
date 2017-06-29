grammar Sql;

sql    :  SELECT FIELDS FROM_STATEMENT ;

FIELDS :  FIELD
       |  FIELD ',' FIELDS
       ;

FROM_STATEMENT : FROM DB_NAME DOT TABLE_NAME ;

FIELD  :  DB_NAME DOT TABLE_NAME DOT FIELD_NAME
       |  DB_NAME DOT TABLE_NAME DOT FIELD_NAME AS ALIAS
       ;

SELECT     : 'select'  ;
FROM       : 'from'    ;
AS         : 'as'      ;

DB_NAME    :  IDENTIFY ;
TABLE_NAME :  IDENTIFY ;
FIELD_NAME :  IDENTIFY ;
ALIAS      :  IDENTIFY ;

DOT        : '.'       ;

IDENTIFY   :  [0-9a-Z]+     ;

WS     :  [ \t\r\n]+ -> skip ;