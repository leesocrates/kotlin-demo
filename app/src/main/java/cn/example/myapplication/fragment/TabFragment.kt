package cn.example.myapplication.fragment

import cn.example.baselib.fragment.BaseFragment
import cn.example.baselib.view.tabswitchwidget.TwoLineTextTabStyle
import cn.example.baselib.view.tagwidget.Tag
import cn.example.baselib.view.tagwidget.TagFactory
import cn.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_tablayout.*

class TabFragment: BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_tablayout
    }

    override fun initView() {
        tabLayoutView.addTab(TwoLineTextTabStyle.TabFactory.getTwoLineTextTab("top", "bottom"))
        tabLayoutView.addTab(TwoLineTextTabStyle.TabFactory.getTwoLineTextTab("top1", "bottom1"))
        tabLayoutView.currentIndex = 0

        val tags =TagFactory.getTagList(arrayOf("hello", "lee", "socrates"))
        tagLayoutView.init(null, null)
        tagLayoutView.showTags(tags)
    }
}