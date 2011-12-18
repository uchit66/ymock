/**
 * Copyright (c) 2011, yMock.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the yMock.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.ymock.util.decors;

import com.ymock.util.DecorException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formattable;
import java.util.Formatter;

/**
 * Format list.
 * @author Yegor Bugayenko (yegor@ymock.com)
 * @version $Id$
 */
public final class ListDecor implements Formattable {

    /**
     * The list.
     */
    private final transient Collection list;

    /**
     * Public ctor.
     * @param obj The object to format
     * @throws DecorException If some problem with it
     */
    public ListDecor(final Object obj) throws DecorException {
        if (obj == null || obj instanceof Collection) {
            this.list = (Collection) obj;
        } else if (obj instanceof Object[]) {
            this.list = Arrays.asList((Object[]) obj);
        } else {
            throw new DecorException("Collection or array required");
        }
    }

    /**
     * {@inheritDoc}
     * @checkstyle ParameterNumber (4 lines)
     */
    @Override
    public void formatTo(final Formatter formatter, final int flags,
        final int width, final int precision) {
        final StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (this.list == null) {
            builder.append("NULL");
        } else {
            boolean first = true;
            for (Object item : this.list) {
                if (!first) {
                    builder.append(", ");
                }
                builder.append(String.format("\"%s\"", item));
                first = false;
            }
        }
        builder.append("]");
        formatter.format("%s", builder.toString());
    }

}
