// $ANTLR 3.4

	package org.qualitune.guery.resource.guery.mopp;


import org.antlr.runtime3_4_0.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class GueryLexer extends Lexer {
    public static final int EOF=-1;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int CARDINALITY=4;
    public static final int CLASSNAME=5;
    public static final int IDENTIFIER=6;
    public static final int LINEBREAK=7;
    public static final int QUOTED_34_34=8;
    public static final int SL_COMMENT=9;
    public static final int WHITESPACE=10;

    	public java.util.List<org.antlr.runtime3_4_0.RecognitionException> lexerExceptions  = new java.util.ArrayList<org.antlr.runtime3_4_0.RecognitionException>();
    	public java.util.List<Integer> lexerExceptionsPosition = new java.util.ArrayList<Integer>();
    	
    	public void reportError(org.antlr.runtime3_4_0.RecognitionException e) {
    		lexerExceptions.add(e);
    		lexerExceptionsPosition.add(((org.antlr.runtime3_4_0.ANTLRStringStream) input).index());
    	}


    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public GueryLexer() {} 
    public GueryLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GueryLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "Guery.g"; }

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:15:7: ( '(' )
            // Guery.g:15:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:16:7: ( ')' )
            // Guery.g:16:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:17:7: ( ',' )
            // Guery.g:17:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:18:7: ( '>' )
            // Guery.g:18:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:19:7: ( '[' )
            // Guery.g:19:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:20:7: ( ']' )
            // Guery.g:20:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:21:7: ( 'and' )
            // Guery.g:21:9: 'and'
            {
            match("and"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:22:7: ( 'by' )
            // Guery.g:22:9: 'by'
            {
            match("by"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:23:7: ( 'connected' )
            // Guery.g:23:9: 'connected'
            {
            match("connected"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:24:7: ( 'find all' )
            // Guery.g:24:9: 'find all'
            {
            match("find all"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:25:7: ( 'group' )
            // Guery.g:25:9: 'group'
            {
            match("group"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:26:7: ( 'motif' )
            // Guery.g:26:9: 'motif'
            {
            match("motif"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:27:7: ( 'not' )
            // Guery.g:27:9: 'not'
            {
            match("not"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:28:7: ( 'prepare' )
            // Guery.g:28:9: 'prepare'
            {
            match("prepare"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:29:7: ( 'select' )
            // Guery.g:29:9: 'select'
            {
            match("select"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:30:7: ( 'where' )
            // Guery.g:30:9: 'where'
            {
            match("where"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:31:7: ( 'with' )
            // Guery.g:31:9: 'with'
            {
            match("with"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2032:11: ( ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* ) )
            // Guery.g:2033:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            {
            // Guery.g:2033:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
            // Guery.g:2033:3: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // Guery.g:2033:27: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Guery.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "CLASSNAME"
    public final void mCLASSNAME() throws RecognitionException {
        try {
            int _type = CLASSNAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2035:10: ( ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )* ) )
            // Guery.g:2036:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )* )
            {
            // Guery.g:2036:2: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )* )
            // Guery.g:2036:3: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )*
            {
            if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // Guery.g:2036:27: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // Guery.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            // Guery.g:2036:60: ( '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='.') ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Guery.g:2036:61: '.' ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            	    {
            	    match('.'); 

            	    if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    // Guery.g:2036:88: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
            	    loop3:
            	    do {
            	        int alt3=2;
            	        int LA3_0 = input.LA(1);

            	        if ( ((LA3_0 >= '0' && LA3_0 <= '9')||(LA3_0 >= 'A' && LA3_0 <= 'Z')||LA3_0=='_'||(LA3_0 >= 'a' && LA3_0 <= 'z')) ) {
            	            alt3=1;
            	        }


            	        switch (alt3) {
            	    	case 1 :
            	    	    // Guery.g:
            	    	    {
            	    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
            	    	        input.consume();
            	    	    }
            	    	    else {
            	    	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	    	        recover(mse);
            	    	        throw mse;
            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop3;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CLASSNAME"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2038:11: ( ( ( ' ' | '\\t' | '\\f' ) ) )
            // Guery.g:2039:2: ( ( ' ' | '\\t' | '\\f' ) )
            {
            if ( input.LA(1)=='\t'||input.LA(1)=='\f'||input.LA(1)==' ' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITESPACE"

    // $ANTLR start "LINEBREAK"
    public final void mLINEBREAK() throws RecognitionException {
        try {
            int _type = LINEBREAK;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2042:10: ( ( ( '\\r\\n' | '\\r' | '\\n' ) ) )
            // Guery.g:2043:2: ( ( '\\r\\n' | '\\r' | '\\n' ) )
            {
            // Guery.g:2043:2: ( ( '\\r\\n' | '\\r' | '\\n' ) )
            // Guery.g:2043:3: ( '\\r\\n' | '\\r' | '\\n' )
            {
            // Guery.g:2043:3: ( '\\r\\n' | '\\r' | '\\n' )
            int alt5=3;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='\r') ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1=='\n') ) {
                    alt5=1;
                }
                else {
                    alt5=2;
                }
            }
            else if ( (LA5_0=='\n') ) {
                alt5=3;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // Guery.g:2043:4: '\\r\\n'
                    {
                    match("\r\n"); 



                    }
                    break;
                case 2 :
                    // Guery.g:2043:13: '\\r'
                    {
                    match('\r'); 

                    }
                    break;
                case 3 :
                    // Guery.g:2043:20: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }


            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LINEBREAK"

    // $ANTLR start "CARDINALITY"
    public final void mCARDINALITY() throws RecognitionException {
        try {
            int _type = CARDINALITY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2046:12: ( ( ( '0' ) | ( '-1' ) | ( '*' ) | ( ( '1' .. '9' ) ( '0' .. '9' )* ) ) )
            // Guery.g:2047:2: ( ( '0' ) | ( '-1' ) | ( '*' ) | ( ( '1' .. '9' ) ( '0' .. '9' )* ) )
            {
            // Guery.g:2047:2: ( ( '0' ) | ( '-1' ) | ( '*' ) | ( ( '1' .. '9' ) ( '0' .. '9' )* ) )
            int alt7=4;
            switch ( input.LA(1) ) {
            case '0':
                {
                alt7=1;
                }
                break;
            case '-':
                {
                alt7=2;
                }
                break;
            case '*':
                {
                alt7=3;
                }
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }

            switch (alt7) {
                case 1 :
                    // Guery.g:2047:3: ( '0' )
                    {
                    // Guery.g:2047:3: ( '0' )
                    // Guery.g:2047:4: '0'
                    {
                    match('0'); 

                    }


                    }
                    break;
                case 2 :
                    // Guery.g:2047:9: ( '-1' )
                    {
                    // Guery.g:2047:9: ( '-1' )
                    // Guery.g:2047:10: '-1'
                    {
                    match("-1"); 



                    }


                    }
                    break;
                case 3 :
                    // Guery.g:2047:16: ( '*' )
                    {
                    // Guery.g:2047:16: ( '*' )
                    // Guery.g:2047:17: '*'
                    {
                    match('*'); 

                    }


                    }
                    break;
                case 4 :
                    // Guery.g:2047:22: ( ( '1' .. '9' ) ( '0' .. '9' )* )
                    {
                    // Guery.g:2047:22: ( ( '1' .. '9' ) ( '0' .. '9' )* )
                    // Guery.g:2047:23: ( '1' .. '9' ) ( '0' .. '9' )*
                    {
                    if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    // Guery.g:2047:33: ( '0' .. '9' )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0 >= '0' && LA6_0 <= '9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // Guery.g:
                    	    {
                    	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "CARDINALITY"

    // $ANTLR start "SL_COMMENT"
    public final void mSL_COMMENT() throws RecognitionException {
        try {
            int _type = SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2049:11: ( ( '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )* ) )
            // Guery.g:2050:2: ( '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )* )
            {
            // Guery.g:2050:2: ( '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )* )
            // Guery.g:2050:3: '//' (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            {
            match("//"); 



            // Guery.g:2050:7: (~ ( '\\n' | '\\r' | '\\uffff' ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\t')||(LA8_0 >= '\u000B' && LA8_0 <= '\f')||(LA8_0 >= '\u000E' && LA8_0 <= '\uFFFE')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // Guery.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFE') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


             _channel = 99; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SL_COMMENT"

    // $ANTLR start "QUOTED_34_34"
    public final void mQUOTED_34_34() throws RecognitionException {
        try {
            int _type = QUOTED_34_34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Guery.g:2053:13: ( ( ( '\"' ) (~ ( '\"' ) )* ( '\"' ) ) )
            // Guery.g:2054:2: ( ( '\"' ) (~ ( '\"' ) )* ( '\"' ) )
            {
            // Guery.g:2054:2: ( ( '\"' ) (~ ( '\"' ) )* ( '\"' ) )
            // Guery.g:2054:3: ( '\"' ) (~ ( '\"' ) )* ( '\"' )
            {
            // Guery.g:2054:3: ( '\"' )
            // Guery.g:2054:4: '\"'
            {
            match('\"'); 

            }


            // Guery.g:2054:8: (~ ( '\"' ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0 >= '\u0000' && LA9_0 <= '!')||(LA9_0 >= '#' && LA9_0 <= '\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Guery.g:
            	    {
            	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            // Guery.g:2054:17: ( '\"' )
            // Guery.g:2054:18: '\"'
            {
            match('\"'); 

            }


            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "QUOTED_34_34"

    public void mTokens() throws RecognitionException {
        // Guery.g:1:8: ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | IDENTIFIER | CLASSNAME | WHITESPACE | LINEBREAK | CARDINALITY | SL_COMMENT | QUOTED_34_34 )
        int alt10=24;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // Guery.g:1:10: T__11
                {
                mT__11(); 


                }
                break;
            case 2 :
                // Guery.g:1:16: T__12
                {
                mT__12(); 


                }
                break;
            case 3 :
                // Guery.g:1:22: T__13
                {
                mT__13(); 


                }
                break;
            case 4 :
                // Guery.g:1:28: T__14
                {
                mT__14(); 


                }
                break;
            case 5 :
                // Guery.g:1:34: T__15
                {
                mT__15(); 


                }
                break;
            case 6 :
                // Guery.g:1:40: T__16
                {
                mT__16(); 


                }
                break;
            case 7 :
                // Guery.g:1:46: T__17
                {
                mT__17(); 


                }
                break;
            case 8 :
                // Guery.g:1:52: T__18
                {
                mT__18(); 


                }
                break;
            case 9 :
                // Guery.g:1:58: T__19
                {
                mT__19(); 


                }
                break;
            case 10 :
                // Guery.g:1:64: T__20
                {
                mT__20(); 


                }
                break;
            case 11 :
                // Guery.g:1:70: T__21
                {
                mT__21(); 


                }
                break;
            case 12 :
                // Guery.g:1:76: T__22
                {
                mT__22(); 


                }
                break;
            case 13 :
                // Guery.g:1:82: T__23
                {
                mT__23(); 


                }
                break;
            case 14 :
                // Guery.g:1:88: T__24
                {
                mT__24(); 


                }
                break;
            case 15 :
                // Guery.g:1:94: T__25
                {
                mT__25(); 


                }
                break;
            case 16 :
                // Guery.g:1:100: T__26
                {
                mT__26(); 


                }
                break;
            case 17 :
                // Guery.g:1:106: T__27
                {
                mT__27(); 


                }
                break;
            case 18 :
                // Guery.g:1:112: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 19 :
                // Guery.g:1:123: CLASSNAME
                {
                mCLASSNAME(); 


                }
                break;
            case 20 :
                // Guery.g:1:133: WHITESPACE
                {
                mWHITESPACE(); 


                }
                break;
            case 21 :
                // Guery.g:1:144: LINEBREAK
                {
                mLINEBREAK(); 


                }
                break;
            case 22 :
                // Guery.g:1:154: CARDINALITY
                {
                mCARDINALITY(); 


                }
                break;
            case 23 :
                // Guery.g:1:166: SL_COMMENT
                {
                mSL_COMMENT(); 


                }
                break;
            case 24 :
                // Guery.g:1:177: QUOTED_34_34
                {
                mQUOTED_34_34(); 


                }
                break;

        }

    }


    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA10_eotS =
        "\7\uffff\13\31\5\uffff\2\31\2\uffff\1\46\11\31\1\60\1\uffff\4\31"+
        "\1\65\4\31\1\uffff\4\31\1\uffff\3\31\1\101\1\31\1\uffff\1\103\1"+
        "\104\2\31\1\107\1\uffff\1\31\2\uffff\1\31\1\112\1\uffff\1\31\1\114"+
        "\1\uffff\1\31\1\uffff\1\116\1\uffff";
    static final String DFA10_eofS =
        "\117\uffff";
    static final String DFA10_minS =
        "\1\11\6\uffff\13\56\5\uffff\2\56\2\uffff\13\56\1\uffff\11\56\1\uffff"+
        "\1\56\1\40\2\56\1\uffff\5\56\1\uffff\5\56\1\uffff\1\56\2\uffff\2"+
        "\56\1\uffff\2\56\1\uffff\1\56\1\uffff\1\56\1\uffff";
    static final String DFA10_maxS =
        "\1\172\6\uffff\13\172\5\uffff\2\172\2\uffff\13\172\1\uffff\11\172"+
        "\1\uffff\4\172\1\uffff\5\172\1\uffff\5\172\1\uffff\1\172\2\uffff"+
        "\2\172\1\uffff\2\172\1\uffff\1\172\1\uffff\1\172\1\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\13\uffff\1\24\1\25\1\26\1\27\1"+
        "\30\2\uffff\1\22\1\23\13\uffff\1\10\11\uffff\1\7\4\uffff\1\15\5"+
        "\uffff\1\12\5\uffff\1\21\1\uffff\1\13\1\14\2\uffff\1\20\2\uffff"+
        "\1\17\1\uffff\1\16\1\uffff\1\11";
    static final String DFA10_specialS =
        "\117\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\22\1\23\1\uffff\1\22\1\23\22\uffff\1\22\1\uffff\1\26\5\uffff"+
            "\1\1\1\2\1\24\1\uffff\1\3\1\24\1\uffff\1\25\12\24\4\uffff\1"+
            "\4\2\uffff\32\21\1\5\1\uffff\1\6\1\uffff\1\21\1\uffff\1\7\1"+
            "\10\1\11\2\21\1\12\1\13\5\21\1\14\1\15\1\21\1\16\2\21\1\17\3"+
            "\21\1\20\3\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\15\30"+
            "\1\27\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\30\30"+
            "\1\33\1\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\16\30"+
            "\1\34\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\10\30"+
            "\1\35\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\21\30"+
            "\1\36\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\16\30"+
            "\1\37\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\16\30"+
            "\1\40\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\21\30"+
            "\1\41\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\42\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\7\30"+
            "\1\43\1\44\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "",
            "",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\3\30"+
            "\1\45\26\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\15\30"+
            "\1\47\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\15\30"+
            "\1\50\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\16\30"+
            "\1\51\13\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\23\30"+
            "\1\52\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\23\30"+
            "\1\53\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\54\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\13\30"+
            "\1\55\16\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\56\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\23\30"+
            "\1\57\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\15\30"+
            "\1\61\14\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\3\30"+
            "\1\62\26\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\24\30"+
            "\1\63\5\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\10\30"+
            "\1\64\21\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\17\30"+
            "\1\66\12\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\67\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\21\30"+
            "\1\70\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\7\30"+
            "\1\71\22\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\72\25\30",
            "\1\73\15\uffff\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30"+
            "\1\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\17\30"+
            "\1\74\12\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\5\30"+
            "\1\75\24\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\1\76"+
            "\31\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\2\30"+
            "\1\77\27\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\100\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\2\30"+
            "\1\102\27\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\21\30"+
            "\1\105\10\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\23\30"+
            "\1\106\6\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\23\30"+
            "\1\110\6\30",
            "",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\111\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\4\30"+
            "\1\113\25\30",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\3\30"+
            "\1\115\26\30",
            "",
            "\1\32\1\uffff\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | IDENTIFIER | CLASSNAME | WHITESPACE | LINEBREAK | CARDINALITY | SL_COMMENT | QUOTED_34_34 );";
        }
    }
 

}