
/* Generated By:JJTree&JavaCC: Do not edit this line. TacParser.java */
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TacParser/* @bgen(jjtree) */ implements TacParserTreeConstants, TacParserConstants
{/* @bgen(jjtree) */
	protected static JJTTacParserState jjtree = new JJTTacParserState();

	public static void main(String[] args) throws ParseException, FileNotFoundException
	{
		if (args.length < 1)
		{
			System.out.println("Please pass in the filename for a parameter.");
			System.exit(1);
		}
		System.out.println("Reading from file " + args[0]);
		TacParser tacParser = new TacParser(new FileInputStream(args[0]));
		SimpleNode root = tacParser.Expression();
		lifetimeAnalysis lf = new lifetimeAnalysis();
		lf.parseNode(root);
		lf.printLifetime(root);
	}

	static final public SimpleNode Expression() throws ParseException
	{
		/* @bgen(jjtree) Expression */
		SimpleNode jjtn000 = new SimpleNode(JJTEXPRESSION);
		boolean jjtc000 = true;
		jjtree.openNodeScope(jjtn000);
		try
		{
			label_1: while (true)
			{
				Expr1();
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk)
				{
				case VARIABLE:
					;
					break;
				default:
					jj_la1[0] = jj_gen;
					break label_1;
				}
			}
			jj_consume_token(0);
			jjtree.closeNodeScope(jjtn000, true);
			jjtc000 = false;
			{
				if (true)
					return jjtn000;
			}
		} catch (Throwable jjte000)
		{
			if (jjtc000)
			{
				jjtree.clearNodeScope(jjtn000);
				jjtc000 = false;
			} else
			{
				jjtree.popNode();
			}
			if (jjte000 instanceof RuntimeException)
			{
				{
					if (true)
						throw (RuntimeException) jjte000;
				}
			}
			if (jjte000 instanceof ParseException)
			{
				{
					if (true)
						throw (ParseException) jjte000;
				}
			}
			{
				if (true)
					throw (Error) jjte000;
			}
		} finally
		{
			if (jjtc000)
			{
				jjtree.closeNodeScope(jjtn000, true);
			}
		}
		throw new Error("Missing return statement in function");
	}

	static final public void Expr1() throws ParseException
	{
		/* @bgen(jjtree) Assign */
		SimpleNode jjtn000 = new SimpleNode(JJTASSIGN);
		Token t;
		boolean jjtc000 = true;
		jjtree.openNodeScope(jjtn000);
		try
		{
			ExprVariable();
			t = jj_consume_token(ASSIGN);
			jjtn000.value = t.image;
			Expr2();
		} catch (Throwable jjte000)
		{
			if (jjtc000)
			{
				jjtree.clearNodeScope(jjtn000);
				jjtc000 = false;
			} else
			{
				jjtree.popNode();
			}
			if (jjte000 instanceof RuntimeException)
			{
				{
					if (true)
						throw (RuntimeException) jjte000;
				}
			}
			if (jjte000 instanceof ParseException)
			{
				{
					if (true)
						throw (ParseException) jjte000;
				}
			}
			{
				if (true)
					throw (Error) jjte000;
			}
		} finally
		{
			if (jjtc000)
			{
				jjtree.closeNodeScope(jjtn000, true);
			}
		}
	}

	static final public void Expr2() throws ParseException
	{
		ExprNumber();
		Token t;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk)
		{
		case PLUS:
		case SUBT:
		case MULT:
		case DIV:
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk)
			{
			case PLUS:
				t = jj_consume_token(PLUS);
				SimpleNode jjtn001 = new SimpleNode(JJTADD);
				jjtn001.value = t.image;
				boolean jjtc001 = true;
				jjtree.openNodeScope(jjtn001);
				try
				{
					ExprNumber();
				} catch (Throwable jjte001)
				{
					if (jjtc001)
					{
						jjtree.clearNodeScope(jjtn001);
						jjtc001 = false;
					} else
					{
						jjtree.popNode();
					}
					if (jjte001 instanceof RuntimeException)
					{
						{
							if (true)
								throw (RuntimeException) jjte001;
						}
					}
					if (jjte001 instanceof ParseException)
					{
						{
							if (true)
								throw (ParseException) jjte001;
						}
					}
					{
						if (true)
							throw (Error) jjte001;
					}
				} finally
				{
					if (jjtc001)
					{
						jjtree.closeNodeScope(jjtn001, 2);
					}
				}
				break;
			case SUBT:
				t = jj_consume_token(SUBT);
				SimpleNode jjtn002 = new SimpleNode(JJTSUB);
				jjtn002.value = t.image;
				boolean jjtc002 = true;
				jjtree.openNodeScope(jjtn002);
				try
				{
					ExprNumber();
				} catch (Throwable jjte002)
				{
					if (jjtc002)
					{
						jjtree.clearNodeScope(jjtn002);
						jjtc002 = false;
					} else
					{
						jjtree.popNode();
					}
					if (jjte002 instanceof RuntimeException)
					{
						{
							if (true)
								throw (RuntimeException) jjte002;
						}
					}
					if (jjte002 instanceof ParseException)
					{
						{
							if (true)
								throw (ParseException) jjte002;
						}
					}
					{
						if (true)
							throw (Error) jjte002;
					}
				} finally
				{
					if (jjtc002)
					{
						jjtree.closeNodeScope(jjtn002, 2);
					}
				}
				break;
			case MULT:
				t = jj_consume_token(MULT);
				SimpleNode jjtn003 = new SimpleNode(JJTMUL);
				jjtn003.value = t.image;
				boolean jjtc003 = true;
				jjtree.openNodeScope(jjtn003);
				try
				{
					ExprNumber();
				} catch (Throwable jjte003)
				{
					if (jjtc003)
					{
						jjtree.clearNodeScope(jjtn003);
						jjtc003 = false;
					} else
					{
						jjtree.popNode();
					}
					if (jjte003 instanceof RuntimeException)
					{
						{
							if (true)
								throw (RuntimeException) jjte003;
						}
					}
					if (jjte003 instanceof ParseException)
					{
						{
							if (true)
								throw (ParseException) jjte003;
						}
					}
					{
						if (true)
							throw (Error) jjte003;
					}
				} finally
				{
					if (jjtc003)
					{
						jjtree.closeNodeScope(jjtn003, 2);
					}
				}
				break;
			case DIV:
				t = jj_consume_token(DIV);
				SimpleNode jjtn004 = new SimpleNode(JJTDIV);
				jjtn004.value = t.image;
				boolean jjtc004 = true;
				jjtree.openNodeScope(jjtn004);
				try
				{
					ExprNumber();
				} catch (Throwable jjte004)
				{
					if (jjtc004)
					{
						jjtree.clearNodeScope(jjtn004);
						jjtc004 = false;
					} else
					{
						jjtree.popNode();
					}
					if (jjte004 instanceof RuntimeException)
					{
						{
							if (true)
								throw (RuntimeException) jjte004;
						}
					}
					if (jjte004 instanceof ParseException)
					{
						{
							if (true)
								throw (ParseException) jjte004;
						}
					}
					{
						if (true)
							throw (Error) jjte004;
					}
				} finally
				{
					if (jjtc004)
					{
						jjtree.closeNodeScope(jjtn004, 2);
					}
				}
				break;
			default:
				jj_la1[1] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[2] = jj_gen;
			;
		}
	}

	static final public void ExprNumber() throws ParseException
	{
		Token t;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk)
		{
		case NUMBER:
			t = jj_consume_token(NUMBER);
			SimpleNode num001 = new SimpleNode(JJTNUMBER);
			num001.value = t.image;
			boolean jjt001 = true;
			jjtree.openNodeScope(num001);
			try
			{
				jjtree.closeNodeScope(num001, true);
				jjt001 = false;

			} finally
			{
				if (jjt001)
				{
					jjtree.closeNodeScope(num001, true);
				}
			}
			break;
		case VARIABLE:
			t = jj_consume_token(VARIABLE);
			SimpleNode jjtn001 = new SimpleNode(JJTT);
			jjtn001.value = t.image;
			boolean jjtc001 = true;
			jjtree.openNodeScope(jjtn001);
			try
			{
				jjtree.closeNodeScope(jjtn001, true);
				jjtc001 = false;

			} finally
			{
				if (jjtc001)
				{
					jjtree.closeNodeScope(jjtn001, true);
				}
			}
			break;
		default:
			jj_la1[3] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
	}

	static final public void ExprVariable() throws ParseException
	{
		Token t;
		t = jj_consume_token(VARIABLE);
		SimpleNode jjtn001 = new SimpleNode(JJTVARIABLE);
		jjtn001.value = t.image;
		boolean jjtc001 = true;
		jjtree.openNodeScope(jjtn001);
		try
		{
			jjtree.closeNodeScope(jjtn001, true);
			jjtc001 = false;

		} finally
		{
			if (jjtc001)
			{
				jjtree.closeNodeScope(jjtn001, true);
			}
		}
	}

	static private boolean jj_initialized_once = false;
	/** Generated Token Manager. */
	static public TacParserTokenManager token_source;
	static SimpleCharStream jj_input_stream;
	/** Current token. */
	static public Token token;
	/** Next token. */
	static public Token jj_nt;
	static private int jj_ntk;
	static private int jj_gen;
	static final private int[] jj_la1 = new int[4];
	static private int[] jj_la1_0;
	static
	{
		jj_la1_init_0();
	}

	private static void jj_la1_init_0()
	{
		jj_la1_0 = new int[]
		{ 0x800, 0x3c0, 0x3c0, 0xc00, };
	}

	/** Constructor with InputStream. */
	public TacParser(java.io.InputStream stream)
	{
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public TacParser(java.io.InputStream stream, String encoding)
	{
		if (jj_initialized_once)
		{
			System.out.println("ERROR: Second call to constructor of static parser.  ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		try
		{
			jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
		token_source = new TacParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream)
	{
		ReInit(stream, null);
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream, String encoding)
	{
		try
		{
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jjtree.reset();
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Constructor. */
	public TacParser(java.io.Reader stream)
	{
		if (jj_initialized_once)
		{
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		jj_input_stream = new SimpleCharStream(stream, 1, 1);
		token_source = new TacParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	static public void ReInit(java.io.Reader stream)
	{
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jjtree.reset();
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Constructor with generated Token Manager. */
	public TacParser(TacParserTokenManager tm)
	{
		if (jj_initialized_once)
		{
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	/** Reinitialise. */
	public void ReInit(TacParserTokenManager tm)
	{
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jjtree.reset();
		jj_gen = 0;
		for (int i = 0; i < 4; i++)
			jj_la1[i] = -1;
	}

	static private Token jj_consume_token(int kind) throws ParseException
	{
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind)
		{
			jj_gen++;
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	/** Get the next Token. */
	static final public Token getNextToken()
	{
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	static final public Token getToken(int index)
	{
		Token t = token;
		for (int i = 0; i < index; i++)
		{
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	static private int jj_ntk()
	{
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	static private int[] jj_expentry;
	static private int jj_kind = -1;

	/** Generate ParseException. */
	static public ParseException generateParseException()
	{
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[12];
		if (jj_kind >= 0)
		{
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 4; i++)
		{
			if (jj_la1[i] == jj_gen)
			{
				for (int j = 0; j < 32; j++)
				{
					if ((jj_la1_0[i] & (1 << j)) != 0)
					{
						la1tokens[j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 12; i++)
		{
			if (la1tokens[i])
			{
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++)
		{
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	static final public void enable_tracing()
	{
	}

	/** Disable tracing. */
	static final public void disable_tracing()
	{
	}

}
