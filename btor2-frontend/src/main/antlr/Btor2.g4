// WARNING: this grammar is NOT an official BTOR2 grammar and will accept invalid btor2 circuits.
// Check your circuit with catbtor before using this grammar!
grammar Btor2;

// Lexer rules
// Csak az aritmetikai és bit operátorokat tartom meg
WS: [ ]+ -> skip;
NUM: [0-9]+;
PLUS: '+';
MINUS: '-';
UNARYOP: 'not'
    | 'inc' | 'dec' | 'neg';
BINOP: 'and' | 'nand' | 'nor' | 'or' | 'xor'
    | 'eq' | 'neq'
    | 'slt' | 'sle' | 'sgt' | 'sgte'
    | 'ult' | 'ule' | 'ugt' | 'ugte'
    | 'add' | 'mul'
    | 'udiv' | 'urem'
    | 'sdiv' | 'srem' | 'smod';
SYMBOL: ~[ \r\n]+;
COMMENT: ';' ~[\r\n]+;

// Parser rules
btor2: (line '\n')* line ('\n')*;

line: comment | node (symbol)? (comment)?;

comment: COMMENT;

nid: NUM;
sid: NUM;

node: bitvec_sort  #sort // sort declaration, no array sort for now
    | (input | state | init | next | property) #stateful
    | (opidx | op) #operation
    | (filled_constant | constant | constant_d | constant_h) #constantNode;

opidx: ext | slice;

ext: id=nid ('uext'|'sext') sid opd1=nid w=NUM;
slice: id=nid 'slice' sid opd1=nid u=NUM l=NUM;
// Terop kivéve
op: binop | unop ;

binop: id=nid BINOP sid opd1=nid opd2=nid;
unop: id=nid UNARYOP sid opd1=nid;

input: id=nid ('input') sid;

init: id=nid 'init' sid param1=nid param2=nid;
next: id=nid 'next' sid param1=nid param2=nid;

state: id=nid 'state' sid;

property: id=nid property_type=('bad' | 'constraint' | 'fair' | 'output' | 'justice' ) param=nid;

// array_sort: id=sid 'sort array' sid1=sid sid2=sid;
bitvec_sort: id=sid 'sort bitvec' width=NUM;

constant: id=nid 'const' sid bin=NUM;
constant_d: id=nid 'constd' sid (MINUS)? dec=NUM;
constant_h: id=nid 'consth' sid hex=SYMBOL;
filled_constant: id=nid fill=('one' | 'ones' | 'zero') sid;

symbol: SYMBOL;