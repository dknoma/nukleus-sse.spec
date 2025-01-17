/**
 * Copyright 2016-2019 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.specification.sse;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.rules.RuleChain.outerRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.kaazing.k3po.junit.annotation.ScriptProperty;
import org.kaazing.k3po.junit.annotation.Specification;
import org.kaazing.k3po.junit.rules.K3poRule;

public class ReconnectIT
{
    private final K3poRule k3po = new K3poRule()
        .addScriptRoot("scripts", "org/reaktivity/specification/sse/reconnect");

    private final TestRule timeout = new DisableOnDebug(new Timeout(10, SECONDS));

    @Rule
    public final TestRule chain = outerRule(k3po).around(timeout);

    @Test
    @Specification({
        "${scripts}/request.header.last.event.id/request",
        "${scripts}/request.header.last.event.id/response" })
    @ScriptProperty("serverTransport \"nukleus://streams/sse#0\"")
    public void shouldReconnectWithRequestHeaderLastEventIdAfterReceivingIdOnly() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.header.last.event.id.fragmented/request",
        "${scripts}/request.header.last.event.id.fragmented/response" })
    @ScriptProperty("serverTransport \"nukleus://streams/sse#0\"")
    public void shouldReconnectWithRequestHeaderLastEventIdWithFlowControlAfterReceivingIdOnly() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.header.last.event.id.and.data/request",
        "${scripts}/request.header.last.event.id.and.data/response" })
    @ScriptProperty("serverTransport \"nukleus://streams/sse#0\"")
    public void shouldReconnectWithRequestHeaderLastEventIdAfterReceivingIdAndData() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/request.header.and.parameter.last.event.id/request",
        "${scripts}/request.header.and.parameter.last.event.id/response" })
    @ScriptProperty("serverTransport \"nukleus://streams/sse#0\"")
    public void shouldReconnectWithRequestHeaderAndParameterLastEventId() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.status.code.200/request",
        "${scripts}/response.status.code.200/response" })
    @ScriptProperty("serverTransport \"nukleus://streams/sse#0\"")
    public void shouldReconnectWhenResponseStatus200() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }

    @Test
    @Specification({
        "${scripts}/response.status.code.500/request",
        "${scripts}/response.status.code.500/response" })
    @ScriptProperty("serverTransport \"nukleus://streams/sse#0\"")
    public void shouldReconnectWhenResponseStatus500() throws Exception
    {
        k3po.start();
        k3po.notifyBarrier("ROUTED_SERVER");
        k3po.finish();
    }
}
