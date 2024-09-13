// Generated from IsiLanguage.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link IsiLanguageParser}.
 */
public interface IsiLanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(IsiLanguageParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(IsiLanguageParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#declara}.
	 * @param ctx the parse tree
	 */
	void enterDeclara(IsiLanguageParser.DeclaraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#declara}.
	 * @param ctx the parse tree
	 */
	void exitDeclara(IsiLanguageParser.DeclaraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(IsiLanguageParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(IsiLanguageParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(IsiLanguageParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(IsiLanguageParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(IsiLanguageParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(IsiLanguageParser.TipoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(IsiLanguageParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(IsiLanguageParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(IsiLanguageParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(IsiLanguageParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(IsiLanguageParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(IsiLanguageParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdAttrib(IsiLanguageParser.CmdAttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdAttrib(IsiLanguageParser.CmdAttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void enterCmdSe(IsiLanguageParser.CmdSeContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdSe}.
	 * @param ctx the parse tree
	 */
	void exitCmdSe(IsiLanguageParser.CmdSeContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdEnquanto(IsiLanguageParser.CmdEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdEnquanto(IsiLanguageParser.CmdEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void enterCmdFacaEnquanto(IsiLanguageParser.CmdFacaEnquantoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#cmdFacaEnquanto}.
	 * @param ctx the parse tree
	 */
	void exitCmdFacaEnquanto(IsiLanguageParser.CmdFacaEnquantoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(IsiLanguageParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(IsiLanguageParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(IsiLanguageParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(IsiLanguageParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#expr_ad}.
	 * @param ctx the parse tree
	 */
	void enterExpr_ad(IsiLanguageParser.Expr_adContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#expr_ad}.
	 * @param ctx the parse tree
	 */
	void exitExpr_ad(IsiLanguageParser.Expr_adContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#termo_ad}.
	 * @param ctx the parse tree
	 */
	void enterTermo_ad(IsiLanguageParser.Termo_adContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#termo_ad}.
	 * @param ctx the parse tree
	 */
	void exitTermo_ad(IsiLanguageParser.Termo_adContext ctx);
	/**
	 * Enter a parse tree produced by {@link IsiLanguageParser#fator}.
	 * @param ctx the parse tree
	 */
	void enterFator(IsiLanguageParser.FatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link IsiLanguageParser#fator}.
	 * @param ctx the parse tree
	 */
	void exitFator(IsiLanguageParser.FatorContext ctx);
}