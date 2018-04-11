using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositorPattern
{
    public abstract class Compositor
    {
        internal abstract void Compose(Composite composite);
    }
}
