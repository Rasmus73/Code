using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositePattern
{
    internal interface IComposite
    {
        DateTime GetMaxDateTime();
        DateTime GetMinDateTime();


        void CalculatePosition(int width, int resolutionInPixels);
    }   

}