using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary.CompositePattern
{
    public abstract class Component
    {
        //private readonly string _name;
        //public string Name => _name;

        // Draw Coordinate? ellers start med at prøve at lave ComponentInterval og se...

        public string Name { get; }

        public Component(string name)
        {
            Name = name;
        }

        public abstract void Add(Component component);
        public abstract void Remove(Component component);
//        public abstract Component GetChild();
        public abstract void Draw(System.Drawing.Graphics graphics);
        //public abstract void Draw(System.Drawing.Graphics graphics, int x, int y); ????
        //public abstract void Display(int depth);
    }
}