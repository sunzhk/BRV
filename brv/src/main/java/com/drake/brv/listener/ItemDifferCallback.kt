/*
 * MIT License
 *
 * Copyright (c) 2023 劉強東 https://github.com/liangjingkanji
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.drake.brv.listener

import androidx.recyclerview.widget.RecyclerView

/**
 * 数据对比默认使用`equals`函数对比, 你可以为数据手动实现equals函数来修改对比逻辑. 推荐定义数据为 data class, 因其会根据构造参数自动生成equals
 */
interface ItemDifferCallback {

    companion object DEFAULT : ItemDifferCallback

    /**
     * 判断新旧数据是否相等
     *
     * @param oldItem 旧数据
     * @param newItem 新数据
     * @return 新旧数据是否相等
     */
    fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    /**
     * 检查新旧数据内容是否相等
     * 例如同一个对象, 但是其属性发生变化需要触发RecyclerView更新列表时应当该方法返回true
     *
     * 该方法只有在[areItemsTheSame]返回true时才会调用
     *
     * @param oldItem 旧数据
     * @param newItem 新数据
     * @return 新旧数据的内容是否相等
     */
    fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }

    /**
     * 当 [areItemsTheSame] 返回true, [areContentsTheSame] 返回false时, 调用该方法
     * 该方法将返回一个用于更新的数据对象
     * @return 如果返回null则item会有闪屏动画, 返回非null则不会. 该返回值会被[com.drake.brv.BindingAdapter.onPayload]接受. 默认实现返回null
     * @see [RecyclerView.Adapter.onBindViewHolder]
     * @see [RecyclerView.Adapter.notifyItemChanged]
     */
    fun getChangePayload(oldItem: Any, newItem: Any): Any? {
        return null
    }
}