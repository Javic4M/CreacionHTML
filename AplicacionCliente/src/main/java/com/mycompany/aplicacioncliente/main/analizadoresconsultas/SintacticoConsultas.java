
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package com.mycompany.aplicacioncliente.main.analizadoresconsultas;

import java_cup.runtime.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class SintacticoConsultas extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public SintacticoConsultas() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public SintacticoConsultas(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public SintacticoConsultas(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\020\000\002\002\004\000\002\002\005\000\002\002" +
    "\005\000\002\002\005\000\002\002\005\000\002\003\004" +
    "\000\002\003\004\000\002\003\004\000\002\003\004\000" +
    "\002\003\004\000\002\003\004\000\002\004\007\000\002" +
    "\004\006\000\002\005\011\000\002\005\010\000\002\006" +
    "\006" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\052\000\004\004\004\001\002\000\012\005\012\006" +
    "\011\007\010\010\007\001\002\000\004\002\006\001\002" +
    "\000\004\002\001\001\002\000\016\015\042\016\040\017" +
    "\045\020\043\021\044\022\041\001\002\000\004\011\034" +
    "\001\002\000\004\011\022\001\002\000\004\011\013\001" +
    "\002\000\004\023\015\001\002\000\004\002\000\001\002" +
    "\000\004\011\016\001\002\000\006\012\017\014\020\001" +
    "\002\000\004\011\013\001\002\000\004\002\ufff5\001\002" +
    "\000\004\002\ufff6\001\002\000\004\023\024\001\002\000" +
    "\004\002\uffff\001\002\000\004\013\025\001\002\000\004" +
    "\023\026\001\002\000\004\011\027\001\002\000\006\012" +
    "\030\014\031\001\002\000\004\011\022\001\002\000\004" +
    "\002\ufff3\001\002\000\004\002\ufff4\001\002\000\004\002" +
    "\ufffe\001\002\000\004\023\035\001\002\000\004\011\036" +
    "\001\002\000\004\014\037\001\002\000\004\002\ufff2\001" +
    "\002\000\004\011\022\001\002\000\004\011\022\001\002" +
    "\000\004\011\022\001\002\000\004\011\022\001\002\000" +
    "\004\011\022\001\002\000\004\011\022\001\002\000\004" +
    "\002\ufffd\001\002\000\004\002\ufffa\001\002\000\004\002" +
    "\ufff8\001\002\000\004\002\ufff9\001\002\000\004\002\ufffc" +
    "\001\002\000\004\002\ufff7\001\002\000\004\002\ufffb\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\052\000\004\002\004\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\003\045\001\001" +
    "\000\004\006\032\001\001\000\004\005\022\001\001\000" +
    "\004\004\013\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\004\020\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\004\005\031\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\004\005\053\001\001\000\004\005\052\001" +
    "\001\000\004\005\051\001\001\000\004\005\050\001\001" +
    "\000\004\005\047\001\001\000\004\005\046\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$SintacticoConsultas$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$SintacticoConsultas$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$SintacticoConsultas$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}




    private Symbol symbol;
    private String contenido;

    public SintacticoConsultas(LexicoConsultas lexico) {
        super(lexico);
    }

    public void syntax_error(Symbol cur_token) {
        symbol = cur_token;
        contenido = (String)(cur_token.value);
    }

    public Symbol getSymbol() {
        return this.symbol;
    }

    public String getContenido() {
        return this.contenido;
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$SintacticoConsultas$actions {
  private final SintacticoConsultas parser;

  /** Constructor */
  CUP$SintacticoConsultas$actions(SintacticoConsultas parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$SintacticoConsultas$do_action_part00000000(
    int                        CUP$SintacticoConsultas$act_num,
    java_cup.runtime.lr_parser CUP$SintacticoConsultas$parser,
    java.util.Stack            CUP$SintacticoConsultas$stack,
    int                        CUP$SintacticoConsultas$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$SintacticoConsultas$result;

      /* select the action based on the action number */
      switch (CUP$SintacticoConsultas$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= inicio EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)).value;
		RESULT = start_val;
              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$SintacticoConsultas$parser.done_parsing();
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // inicio ::= CONSULTAR VISITAS_SITIO id1 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("inicio",0, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-2)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // inicio ::= CONSULTAR VISITAS_PAGINA id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("inicio",0, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-2)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // inicio ::= CONSULTAR PAGINAS_POPULARES id3 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("inicio",0, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-2)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // inicio ::= CONSULTAR COMPONENTE componente 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("inicio",0, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-2)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // componente ::= TODOS id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("componente",1, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // componente ::= TITULO id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("componente",1, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // componente ::= PARRAFO id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("componente",1, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // componente ::= IMAGEN id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("componente",1, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // componente ::= VIDEO id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("componente",1, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // componente ::= MENU id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("componente",1, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-1)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // id1 ::= COMILLA PALABRA COMILLA COMA id1 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("id1",2, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-4)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // id1 ::= COMILLA PALABRA COMILLA PUNTO_Y_COMA 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("id1",2, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-3)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // id2 ::= COMILLA PALABRA PUNTO PALABRA COMILLA COMA id2 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("id2",3, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-6)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // id2 ::= COMILLA PALABRA PUNTO PALABRA COMILLA PUNTO_Y_COMA 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("id2",3, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-5)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // id3 ::= COMILLA PALABRA COMILLA PUNTO_Y_COMA 
            {
              Object RESULT =null;

              CUP$SintacticoConsultas$result = parser.getSymbolFactory().newSymbol("id3",4, ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.elementAt(CUP$SintacticoConsultas$top-3)), ((java_cup.runtime.Symbol)CUP$SintacticoConsultas$stack.peek()), RESULT);
            }
          return CUP$SintacticoConsultas$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$SintacticoConsultas$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$SintacticoConsultas$do_action(
    int                        CUP$SintacticoConsultas$act_num,
    java_cup.runtime.lr_parser CUP$SintacticoConsultas$parser,
    java.util.Stack            CUP$SintacticoConsultas$stack,
    int                        CUP$SintacticoConsultas$top)
    throws java.lang.Exception
    {
              return CUP$SintacticoConsultas$do_action_part00000000(
                               CUP$SintacticoConsultas$act_num,
                               CUP$SintacticoConsultas$parser,
                               CUP$SintacticoConsultas$stack,
                               CUP$SintacticoConsultas$top);
    }
}

}
