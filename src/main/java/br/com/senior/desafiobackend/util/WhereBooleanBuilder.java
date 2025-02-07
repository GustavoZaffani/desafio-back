package br.com.senior.desafiobackend.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Visitor;
import com.querydsl.core.types.dsl.BooleanExpression;

import javax.annotation.Nullable;

public class WhereBooleanBuilder implements Predicate {

    private BooleanBuilder delegate;

    public WhereBooleanBuilder() {
        this.delegate = new BooleanBuilder();
    }

    public WhereBooleanBuilder(Predicate pPredicate) {
        this.delegate = new BooleanBuilder(pPredicate);
    }

    private WhereBooleanBuilder and(Predicate right) {
        return new WhereBooleanBuilder(delegate.and(right));
    }

    public <V> WhereBooleanBuilder optionalAnd(@Nullable V pValue, LazyBooleanExpression pBooleanExpression) {
        if (pValue != null) {
            return and(pBooleanExpression.get());
        }
        return this;
    }

    @FunctionalInterface
    public interface LazyBooleanExpression {
        BooleanExpression get();
    }

    @Override
    public <R, C> R accept(Visitor<R, C> v, C context) {
        return this.delegate.accept(v, context);
    }

    @Override
    public Class<? extends Boolean> getType() {
        return this.delegate.getType();
    }

    @Override
    public Predicate not() {
        return this.delegate.not();
    }
}
