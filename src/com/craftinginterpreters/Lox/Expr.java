//> Appendix II expr
package com.craftinginterpreters.Lox;

import java.util.List;

abstract class Expr {
  interface Visitor<R> {
    R visitBinaryExpr(Binary expr);
    R visitGroupingExpr(Grouping expr);
    R visitLiteralExpr(Literal expr);
    R visitUnaryExpr(Unary expr);
    R visitConditionalExpr(Conditional expr);
  }
  static class Conditional extends Expr {
	    Conditional(Expr condition, Expr thenBranch, Expr elseBranch) {
	        this.condition = condition;
	        this.thenBranch = thenBranch;
	        this.elseBranch = elseBranch;
	    }

	    @Override
	    <R> R accept(Visitor<R> visitor) {
	        return visitor.visitConditionalExpr(this);
	    }

	    final Expr condition;
	    final Expr thenBranch;
	    final Expr elseBranch;
	}

  // Nested Expr classes here...
//> expr-binary
  static class Binary extends Expr {
    Binary(Expr left, Token operator, Expr right) {
      this.left = left;
      this.operator = operator;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitBinaryExpr(this);
    }

    final Expr left;
    final Token operator;
    final Expr right;
  }
//< expr-binary
//> expr-grouping
  static class Grouping extends Expr {
    Grouping(Expr expression) {
      this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitGroupingExpr(this);
    }

    final Expr expression;
  }
//< expr-grouping
//> expr-literal
  static class Literal extends Expr {
    Literal(Object value) {
      this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitLiteralExpr(this);
    }

    final Object value;
  }
//< expr-literal
//> expr-unary
  static class Unary extends Expr {
    Unary(Token operator, Expr right) {
      this.operator = operator;
      this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
      return visitor.visitUnaryExpr(this);
    }

    final Token operator;
    final Expr right;
  }
//< expr-unary

  abstract <R> R accept(Visitor<R> visitor);
}
//< Appendix II expr
