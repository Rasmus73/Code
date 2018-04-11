using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Model.ValueTypes;

namespace CompositeClassLibrary.CompositePattern
{
    public class ComponentInterval : IntervalComponent, IComponent
    {
        public string Name { get; set; }

        public ComponentInterval(string name, IntervalType interval)
            : base(interval)
        {
            Name = name;
        }

        public void Add(IComponent component)
        {
            throw new NotImplementedException();
        }

        public void Remove(IComponent component)
        {
            throw new NotImplementedException();
        }

        public void Draw(Graphics graphics)
        {
            System.Drawing.Font font = new Font("TimesNewRoman", 8);
            graphics.DrawString(Name, font, new SolidBrush(Color.Black), 1, 80);
        }
    }
}