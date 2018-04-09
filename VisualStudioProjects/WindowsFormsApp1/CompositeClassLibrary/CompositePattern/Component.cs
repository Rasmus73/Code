using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositePattern
{
    public interface IComponent
    {
        string Name { get; set; }

        void Add(IComponent component);
        void Remove(IComponent component);
        IComponent GetChild(int index);
        void Draw(System.Drawing.Graphics graphics);
    }
}