package syntaxAnalysis;
import fileReader.TextFileReader;
import lexicalAnalysis.Scanner;
import lexicalAnalysis.Token;

public class Parser {

	Scanner scanner;
	Token currentToken;
	
	public Parser(TextFileReader file)
	{
		scanner = new Scanner(file);
	}
	public void parse()
	{
		currentToken = scanner.scan();
		while(currentToken != null) {
			System.out.println(currentToken.toString());
			currentToken = scanner.scan();
		}
	}
	public void accept(int expectedType) {
		if (currentToken.getType() == expectedType)
			currentToken = scanner.scan();
		else {	//	Erro na análise sintática
				//	Não foi possível achar regra de derivação - token inesperado
			System.out.println("ERROR - SYNTAX\nUnexpected token read: [" + currentToken.getSpelling() +
					"] (token type " + currentToken.getType() + "), in line " + currentToken.getLine() + 
					" column "+ currentToken.getColumn() + 
					", while it was expected a \"" + Token.spellings[expectedType] + 
					"\" (token type " + expectedType + ")." 
					);
		}
	}
	public void accept() {	//	acceptIt();
			currentToken = scanner.scan();
	}

	private void parseAtribuição() {	//	<atribuição> ::= 
										//		<variável> := <expressão>
		parseVariável();
		accept(Token.OPATTRIB);
		parseExpressão();
	}
	private void parseBoolLit() {	//	<bool-lit> ::= 
									//		true 
									//		| false
		if (currentToken.getType() == Token.TRUE)
			accept();
		else if (currentToken.getType() == Token.FALSE)
			accept();
		else {
			// Escrever mensagem de erro sintático
		}
	}
	private void parseChamadaDeFunção() {	//	<chamada-de-função> ::= 
											//		id "(" ( <lista-de-expressões> | <vazio> ) ")"
		accept(Token.ID);
		accept(Token.LPARENTHESIS);
											//	first1( <lista-de-expressões> ) = {id, true, false, int-lit, float-lit, "(" }
		if (	currentToken.getType() == Token.ID || currentToken.getType() == Token.TRUE ||
				currentToken.getType() == Token.FALSE || currentToken.getType() == Token.INTLITERAL || 
				currentToken.getType() == Token.FLOATLITERAL || currentToken.getType() == Token.LPARENTHESIS
				)
			accept();
											//	follow1( ( <lista-de-expressões> | <vazio> ) ) = { “)” }
		else if (currentToken.getType() == Token.RPARENTHESIS) 
			accept();
		else {
			// Escrever mensagem de erro sintático
		}
	}
	private void parseChamadaDeProcedimento() {	//	<chamada-de-procedimento> ::= 
												//		id "(" ( <lista-de-expressões> | <vazio> ) ")"
		accept(Token.ID);
		accept(Token.LPARENTHESIS);
												//	first1( <lista-de-expressões> ) = {id, true, false, int-lit, float-lit, "(" }
		if (	currentToken.getType() == Token.ID || currentToken.getType() == Token.TRUE ||
				currentToken.getType() == Token.FALSE || currentToken.getType() == Token.INTLITERAL ||
				currentToken.getType() == Token.FLOATLITERAL || currentToken.getType() == Token.LPARENTHESIS
				)
			accept();
												//	follow1( ( <lista-de-expressões> | <vazio> ) ) = { “)” }
		accept(Token.RPARENTHESIS);	
	}
	private void parseComando() {	//	<comando> ::= 
									//		id ( <seletor> := <expressão> | "(" ( <lista-de-expressões> | <vazio> ) ")" )
									//		| <condicional> 
									//		| <iterativo> 
									//		| <comando-composto>
		if (currentToken.getType() == Token.ID) {
			accept();
			if (currentToken.getType() == Token.LBRACKET || currentToken.getType() == Token.OPATTRIB) {
				parseSeletor();
				accept(Token.OPATTRIB);
				parseExpressão();
			}
			else if (currentToken.getType() == Token.LPARENTHESIS) {
				accept();
									//	first1( <lista-de-expressões> ) = {id, true, false, int-lit, float-lit, "(" }
				if (	currentToken.getType() == Token.ID || currentToken.getType() == Token.TRUE ||
						currentToken.getType() == Token.FALSE || currentToken.getType() == Token.INTLITERAL ||
						currentToken.getType() == Token.FLOATLITERAL || currentToken.getType() == Token.LPARENTHESIS
						)
					accept();
									//	follow1( ( <lista-de-expressões> | <vazio> ) ) = { ")" }
				accept(Token.RPARENTHESIS);	
			}
			else {
				// Escrever mensagem de erro sintático
			}
		}
		else if (currentToken.getType() == Token.IF)
			parseCondicional();
		else if (currentToken.getType() == Token.WHILE)
			parseIterativo();
		else if (currentToken.getType() == Token.BEGIN)
			parseComandoComposto();
		else {
			// Escrever mensagem de erro sintático
		}
	}
	private void parseComandoComposto() {	//	<comando-composto> ::= 
											//		begin <lista-de-comandos> end
		accept(Token.BEGIN);
		parseListaDeComandos();
		accept(Token.END);
	}
	private void parseCondicional() {	//	<condicional> ::= 	
										//		if <expressão> then <comando> ( else <comando> | <vazio> )
		accept(Token.IF);
		parseExpressão();
		accept(Token.THEN);
		parseComando();
		if (currentToken.getType() == Token.ELSE) {	//	Considerando que os elses se referem
			accept();								//	aos "ifs" mais internos
			parseComando();
		}
	}
	private void parseCorpo() {	//	<corpo> ::=
								//		<declarações> <comando-composto>
		parseDeclarações();
		parseComandoComposto();
	}
	private void parseDeclaração() {	//	<declaração> ::=
										//		<declaração-de-variável> 
										//		| <declaração-de-função> 
										//		| <declaração-de-procedimento>
		if (currentToken.getType() == Token.VAR)
			parseDeclaraçãoDeVariável();
		else if (currentToken.getType() == Token.FUNCTION)
			parseDeclaraçãoDeFunção();
		else if (currentToken.getType() == Token.PROCEDURE)
			parseDeclaraçãoDeProcedimento();
		else {
			// Escrever mensagem de erro sintático
		}
	}
	private void parseDeclaraçãoDeFunção() {	//	<declaração-de-função> ::= 
												//		function id ( <lista-de-parâmetros> | <vazio> ) : <tipo-simples> ; <corpo>

		accept(Token.FUNCTION);
		accept(Token.ID);
		if (currentToken.getType() == Token.VAR || currentToken.getType() == Token.ID)
			parseListaDeParâmetros();	
		accept(Token.COLON);
		parseTipoSimples();
		accept(Token.SEMICOLON);
		parseCorpo();
	}
	private void parseDeclaraçãoDeProcedimento() {	//	<declaração-de-procedimento> ::= 
													//		procedure id ( <lista-de-parâmetros> | <vazio> ) ; <corpo>
		accept(Token.PROCEDURE);
		accept(Token.ID);
		if (currentToken.getType() == Token.VAR || currentToken.getType() == Token.ID)
			parseListaDeParâmetros();	
		accept(Token.SEMICOLON);
		parseCorpo();
	}
	private void parseDeclaraçãoDeVariável() {	//	<declaração-de-variável> ::= 
												//		var <lista-de-ids> : <tipo>
		accept(Token.VAR);
		parseListaDeIds();
		accept(Token.COLON);
		parseTipo();
	}
	private void parseDeclarações() {	//	<declarações> ::= 
										//		( <declaração> ; )*
		while(currentToken.getType() == Token.VAR || currentToken.getType() == Token.FUNCTION || 
				currentToken.getType() == Token.PROCEDURE) {
			parseDeclaração();
			accept(Token.SEMICOLON);
		}
	}
	private void parseExpressão() {	//	<expressão> ::= 
									//		<expressão-simples> ( ε | <op-rel> <expressão-simples> ) 
		parseExpressãoSimples();
		if (	currentToken.getType() == Token.OPLOWERTHN || currentToken.getType() == Token.OPGREATTHN || 
				currentToken.getType() == Token.OPLOWOREQ || currentToken.getType() == Token.OPGREOREQ || 
				currentToken.getType() == Token.OPEQUAL || currentToken.getType() == Token.OPDIFF
				) { 	//	{ <, >, <=, >=, =, <> }
			parseOpRel();
			parseExpressãoSimples();
		}
	}
	private void parseExpressãoSimples() {	//	<expressão-simples> ::= 
											//		<termo> ( <op-ad> <termo> )*
		parseTermo();
		if (	currentToken.getType() == Token.OPSUM || currentToken.getType() == Token.OPSUB ||
				currentToken.getType() == Token.OR 
				) {
			parseOpAd();
			parseTermo();
		}
	}
	private void parseFator() {	//	<fator> ::= 
								//		id ( <seletor> | "("( <lista-de-expressões> | <vazio> ) ")" )
								//		| <literal>  | "(" <expressão> ")" 
		if (currentToken.getType() == Token.ID) {
			accept();
			if (currentToken.getType() == Token.LPARENTHESIS) {
				accept();
				if (	currentToken.getType() == Token.ID || currentToken.getType() == Token.TRUE 
						|| currentToken.getType() == Token.FALSE || currentToken.getType() == Token.INTLITERAL  
						|| currentToken.getType() == Token.FLOATLITERAL || currentToken.getType() == Token.LPARENTHESIS
						) {
					parseListaDeExpressões();
				}
				accept(Token.RPARENTHESIS);
			}
			else if (currentToken.getType() == Token.LBRACKET) // DÚVIDA NESSA REGRA <<<<<<-------
				parseSeletor();
		}
		else if (currentToken.getType() == Token.TRUE || currentToken.getType() == Token.FALSE 
				|| currentToken.getType() == Token.INTLITERAL || currentToken.getType() == Token.FLOATLITERAL) {
			parseLiteral();
		}
		else if (currentToken.getType() == Token.LPARENTHESIS) {
			accept();
			parseExpressão();
			accept(Token.RPARENTHESIS);
		}
		else {
			// Escrever mensagem de erro sintático
		}
	}
	private void parseIterativo() {	//	<iterativo> ::= 
									//		while <expressão> do <comando>
		accept(Token.WHILE);
		parseExpressão();
		accept(Token.DO);
		parseComando();
	}
	private void parseListaDeComandos() {	//	<lista-de-comandos> ::= 
											//		( <comando> ; )*
		while (currentToken.getType() == Token.ID || currentToken.getType() == Token.IF ||
				currentToken.getType() == Token.WHILE || currentToken.getType() == Token.BEGIN) {
			parseComando();
			accept(Token.SEMICOLON);
		}
	}
	private void parseListaDeExpressões() {	//	<lista-de-expressões> ::= 
											//		<expressão> ( , <expressão> )*
		parseExpressão();
		while (currentToken.getType() == Token.COMMA) {
			accept();
			parseExpressão();
		}
	}
	private void parseListaDeIds() {	//	<lista-de-ids> ::= 
										//		id ( , id )*
		accept(Token.ID);
		while (currentToken.getType() == Token.COMMA) {
			accept();
			accept(Token.ID);
		}
	}
	private void parseListaDeParâmetros() {	//	<lista-de-parâmetros> ::= 
											//		<parâmetros> ( ; <parâmetros> ) * 
		parseParâmetros();
		while (currentToken.getType() == Token.SEMICOLON) {
			accept();
			parseParâmetros();
		}
	}
	private void parseLiteral() {	//	<literal> ::= 
									//		<bool-lit> | int-lit | float-lit 
		if (currentToken.getType() == Token.TRUE || currentToken.getType() == Token.FALSE) 
			parseBoolLit();
		else if (currentToken.getType() == Token.INTLITERAL)
			accept();
		else if (currentToken.getType() == Token.FLOATLITERAL)
			accept();
		else {
			// Escrever mensagem de erro sintático
		}	
	}
	private void parseOpAd() {	// 	<op-ad> ::= 
								//		+ | - | or
		switch(currentToken.getType()) {
			case Token.OPSUM: case Token.OPSUB: case Token.OR:
				accept();
				break;
			default:
				// Escrever mensagem de erro sintático
		}		
	}
	private void parseOpMul() {	//	<op-mul> ::= 
								//		*  | /  | and
		switch(currentToken.getType()) {
			case Token.OPMULT: case Token.OPDIV: case Token.AND:
				accept();
				break;
			default:
				// Escrever mensagem de erro sintático
		}		
	}
	private void parseOpRel() {	//	<op-rel> ::= 
								//		<  | >  | <=  | >= | = | <>
		switch(currentToken.getType()) {
			case Token.OPLOWERTHN: case Token.OPGREATTHN: case Token.OPLOWOREQ: 
			case Token.OPGREOREQ: case Token.OPEQUAL: case Token.OPDIFF:
				accept();
				break;
			default:
				// Escrever mensagem de erro sintático
		}	

	}
	private void parseParâmetros() {	//	<parâmetros> ::= 
										//		( var | <vazio> ) <lista-de-ids> : <tipo-simples>
		if (currentToken.getType() == Token.VAR)
			accept();
		parseListaDeIds();
		accept(Token.COLON);
		parseTipoSimples();
	}
	
	private void parsePrograma() { 	// <programa> ::= 
									//		program id ; <corpo> .
		accept(Token.PROGRAM);
		accept(Token.ID);
		accept(Token.SEMICOLON);
		parseCorpo();
		accept(Token.DOT);
	}
	
	private void parseSeletor() {	//	<seletor> ::= 
									//		( [ <expressão> ] )*
		while(currentToken.getType() == Token.LBRACKET) {
			accept();
			parseExpressão();
			accept(Token.RBRACKET);
		}
	}
	private void parseTermo() {	//	<termo> ::= 
								//		<fator> ( <op-mul> <fator> )*
		parseFator();
		while (currentToken.getType() == Token.OPMULT || currentToken.getType() == Token.OPDIV
				|| currentToken.getType() == Token.AND) {
			parseOpMul();
			parseFator();
		}
	}
	private void parseTipo() {	//	<tipo> ::= 
								//		<tipo-agregado> | <tipo-simples>
		if (currentToken.getType() == Token.ARRAY)
			parseTipoAgregado();
		else if (currentToken.getType() == Token.INTEGER || currentToken.getType() == Token.REAL
				|| currentToken.getType() == Token.BOOLEAN)
			parseTipoSimples();
		else {
			// Escrever mensagem de erro sintático
		}
	}
	private void parseTipoAgregado() {	//	<tipo-agregado> ::= 
										//		array [ int-lit .. int-lit ] of <tipo>
		accept(Token.ARRAY);
		accept(Token.LBRACKET);
		accept(Token.INTLITERAL);
		accept(Token.TILL);
		accept(Token.INTLITERAL);
		accept(Token.RBRACKET);
		accept(Token.OF);
		parseTipo();
	}
	private void parseTipoSimples() {	//	<tipo-simples> ::= 
										//		integer | real 	| boolean
		switch(currentToken.getType()) {
			case Token.INTEGER: case Token.REAL: case Token.BOOLEAN:
				accept();
				break;
			default:
				// Escrever mensagem de erro sintático
		}		
	}
	private void parseVariável() {	//	<variável> ::= 
									//		id <seletor>
		accept(Token.ID);
		parseSeletor();
	}
	private void parseVazio() {	//	<vazio> ::= 
								//		ε
		
	}
}
