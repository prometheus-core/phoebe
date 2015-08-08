/*
 * Copyright © 2015, 8Kdata Technology S.L.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for any purpose, without fee, and without
 * a written agreement is hereby granted, provided that the above
 * copyright notice and this paragraph and the following two
 * paragraphs appear in all copies.
 *
 * In no event shall 8Kdata Technology S.L. be liable to any party
 * for direct, indirect, special, incidental, or consequential
 * damages, including lost profits, arising out of the use of this
 * software and its documentation, even if 8Kdata Technology S.L.
 * has been advised of the possibility of such damage.
 *
 * 8Kdata Technology S.L. specifically disclaims any warranties,
 * including, but not limited to, the implied warranties of
 * merchantability and fitness for a particular purpose. the
 * software provided hereunder is on an “as is” basis, and
 * 8Kdata Technology S.L. has no obligations to provide
 * maintenance, support, updates, enhancements, or modifications.
 */


package com.eightkdata.phoebe.common.message;

import com.eightkdata.phoebe.common.BaseMessage;
import com.eightkdata.phoebe.common.Encoders;
import com.eightkdata.phoebe.common.MessageType;
import com.google.common.base.MoreObjects;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.nio.charset.Charset;

import static com.eightkdata.phoebe.common.util.Preconditions.checkTextNotNullNotEmpty;

/**
 * @see <a href="http://www.postgresql.org/docs/9.4/interactive/protocol-message-formats.html">Message Formats</a>
 */
@Immutable
public class Query extends BaseMessage {
    private final String query;

    public Query(@Nonnull String query) {
        this.query = checkTextNotNullNotEmpty(query, "query");
    }

    /**
     * Get the query that this message represents.
     * @return the SQL query
     */
    public String getQuery() {
        return query;
    }

    @Override
    public MessageType getType() {
        return MessageType.Query;
    }

    @Override
    public int computePayloadLength(Charset encoding) {
        return Encoders.stringLength(query, encoding);
    }

    @Override
    public void fillInPayloadInformation(MoreObjects.ToStringHelper toStringHelper) {
        toStringHelper.add("query", query);
    }
}
