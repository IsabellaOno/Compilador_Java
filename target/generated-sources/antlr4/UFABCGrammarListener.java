// Generated from UFABCGrammar.g4 by ANTLR 4.4

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link UFABCGrammarParser}.
 */
public interface UFABCGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(@NotNull UFABCGrammarParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(@NotNull UFABCGrammarParser.ExprlContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdIF}.
	 * @param ctx the parse tree
	 */
	void enterCmdIF(@NotNull UFABCGrammarParser.CmdIFContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdIF}.
	 * @param ctx the parse tree
	 */
	void exitCmdIF(@NotNull UFABCGrammarParser.CmdIFContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(@NotNull UFABCGrammarParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(@NotNull UFABCGrammarParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(@NotNull UFABCGrammarParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(@NotNull UFABCGrammarParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(@NotNull UFABCGrammarParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(@NotNull UFABCGrammarParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(@NotNull UFABCGrammarParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(@NotNull UFABCGrammarParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull UFABCGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull UFABCGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(@NotNull UFABCGrammarParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(@NotNull UFABCGrammarParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(@NotNull UFABCGrammarParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(@NotNull UFABCGrammarParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdAttrib(@NotNull UFABCGrammarParser.CmdAttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdAttrib(@NotNull UFABCGrammarParser.CmdAttribContext ctx);
}