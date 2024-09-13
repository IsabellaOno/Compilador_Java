// Generated from IsiLanguage.g4 by ANTLR 4.4

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
 * {@link IsiLanguageParser}.
 */
public interface IsiLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(@NotNull IsiLanguageParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(@NotNull IsiLanguageParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#termo_ad}.
	 * @param ctx the parse tree
	 */
	void enterTermo_ad(@NotNull IsiLanguageParser.Termo_adContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#termo_ad}.
	 * @param ctx the parse tree
	 */
	void exitTermo_ad(@NotNull IsiLanguageParser.Termo_adContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(@NotNull IsiLanguageParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(@NotNull IsiLanguageParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(@NotNull IsiLanguageParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(@NotNull IsiLanguageParser.DeclaraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(@NotNull IsiLanguageParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(@NotNull IsiLanguageParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(@NotNull IsiLanguageParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(@NotNull IsiLanguageParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void enterCmdPara(@NotNull IsiLanguageParser.CmdParaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdPara}.
	 * @param ctx the parse tree
	 */
	void exitCmdPara(@NotNull IsiLanguageParser.CmdParaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(@NotNull IsiLanguageParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(@NotNull IsiLanguageParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(@NotNull IsiLanguageParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(@NotNull IsiLanguageParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdAttrib(@NotNull IsiLanguageParser.CmdAttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdAttrib(@NotNull IsiLanguageParser.CmdAttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(@NotNull IsiLanguageParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(@NotNull IsiLanguageParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(@NotNull IsiLanguageParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(@NotNull IsiLanguageParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#expr_ad}.
	 * @param ctx the parse tree
	 */
	void enterExpr_ad(@NotNull IsiLanguageParser.Expr_adContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#expr_ad}.
	 * @param ctx the parse tree
	 */
	void exitExpr_ad(@NotNull IsiLanguageParser.Expr_adContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(@NotNull IsiLanguageParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(@NotNull IsiLanguageParser.FatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(@NotNull IsiLanguageParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(@NotNull IsiLanguageParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(@NotNull IsiLanguageParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(@NotNull IsiLanguageParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(@NotNull IsiLanguageParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(@NotNull IsiLanguageParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdFacaEnquanto(@NotNull IsiLanguageParser.CmdFacaEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdFacaEnquanto(@NotNull IsiLanguageParser.CmdFacaEnquantoContext ctx);
}