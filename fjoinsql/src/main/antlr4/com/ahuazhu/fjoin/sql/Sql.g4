grammar Sql;

sql    :  SELECT FIELDS FROM_STATEMENT ;

FIELDS :  FIELD
       |  FIELD ',' FIELDS
       ;

FROM_STATEMENT : FROM FULL_TABLE_NAME ;

FULL_TABLE_NAME: DB_NAME DOT TABLE_NAME ;


FIELD  :  FULL_FIELD_NAME
       |  FULL_FIELD_NAME AS ALIAS
       ;

FULL_FIELD_NAME : FULL_TABLE_NAME DOT FIELD_NAME ;

SELECT     : 'select'  ;
FROM       : 'from'    ;
AS         : 'as'      ;

DB_NAME    :  IDENTIFY ;
TABLE_NAME :  IDENTIFY ;
FIELD_NAME :  IDENTIFY ;
ALIAS      :  IDENTIFY ;

DOT        : '.'       ;

IDENTIFY   :  [0-9a-Z]+     ;

WS         :  [ \t\r\n]+ -> skip ;