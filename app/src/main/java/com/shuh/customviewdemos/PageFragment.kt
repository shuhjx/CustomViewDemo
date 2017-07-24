package com.shuh.customviewdemos

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub

/**
 * Created by pc-135 on 2017/7/24.
 */
class PageFragment : Fragment() {

    @LayoutRes private var sampleLayoutRes : Int = 0
    @LayoutRes private var practiceLayoutRes : Int = 0

    companion object {
        @JvmStatic fun newInstance(@LayoutRes sampleLayoutRes : Int, @LayoutRes practiceLayoutRes : Int) : PageFragment{
            var pageFragment : PageFragment = PageFragment()
            val args : Bundle = Bundle()
            args.putInt("sampleLayoutRes", sampleLayoutRes)
            args.putInt("practiceLayoutRes", practiceLayoutRes)
            pageFragment.arguments = args
            return pageFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view : View? = inflater?.inflate(R.layout.fragment_page, container, false)
        var sampleStub : ViewStub? = view?.findViewById(R.id.sampleStub)
        sampleStub?.layoutResource = sampleLayoutRes
        sampleStub?.inflate()

        var practiceStub : ViewStub? = view?.findViewById(R.id.practiceStub)
        practiceStub?.layoutResource = practiceLayoutRes
        practiceStub?.inflate()

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var args : Bundle = arguments
        if(args != null){
            sampleLayoutRes = args.getInt("sampleLayoutRes")
            practiceLayoutRes = args.getInt("practiceLayoutRes")
        }
    }
}
