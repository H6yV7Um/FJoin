grammar Sql;

sql    :  'select' fields from_stat ;

fields :  field
       |  field ',' fields
       ;

from_stat : 'from' full_table_name ;

full_table_name: db_name '.' table_name ;


field  :  full_field_name
       |  full_field_name 'as' alias_name
       ;

full_field_name : full_table_name '.' field_name ;

db_name    : IDENTIFY           ;
table_name : IDENTIFY           ;
alias_name : IDENTIFY           ;
field_name : IDENTIFY           ;

DOT        :  ','              ;

IDENTIFY   :  [_0-9a-zA-Z]+     ;

WS         :  [ \t\r\n]+ -> skip ;